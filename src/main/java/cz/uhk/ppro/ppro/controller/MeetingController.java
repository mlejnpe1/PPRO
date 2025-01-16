package cz.uhk.ppro.ppro.controller;

import cz.uhk.ppro.ppro.model.Meeting;
import cz.uhk.ppro.ppro.service.MeetingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/meetings")
public class MeetingController {

    private MeetingService meetingService;

    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("meetings", meetingService.getAllMeetings());
        return "meeting_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Long id){
        Meeting meeting = meetingService.getMeetingsById(id);
        if(meeting != null){
            model.addAttribute("meeting", meeting);
            return "meeting_detail";
        }
        return "meeting_detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        if(meetingService.getMeetingsById(id)!=null){
            meetingService.deleteMeetingById(id);
        }
        return "redirect:/meetings/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        Meeting meeting = meetingService.getMeetingsById(id);
        if(meeting != null) {
            model.addAttribute("meeting", meeting);
            model.addAttribute("edit", true);
            model.addAttribute("meetings", meetingService.getAllMeetings());
            return "meeting_edit";
        }
        return "redirect:/meetings/";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("meeting", new Meeting());
        model.addAttribute("edit", false);
        return "meeting_edit";
    }

    @PostMapping("/save")
    public String save(@Valid Meeting meeting, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("edit", true);
            return "meeting_edit";
        }
        meetingService.saveMeeting(meeting);
        return "redirect:/meetings/";
    }
}
