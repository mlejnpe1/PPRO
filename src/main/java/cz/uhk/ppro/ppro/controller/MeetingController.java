package cz.uhk.ppro.ppro.controller;

import cz.uhk.ppro.ppro.model.Meeting;
import cz.uhk.ppro.ppro.model.User;
import cz.uhk.ppro.ppro.service.MeetingService;
import cz.uhk.ppro.ppro.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/meetings")
public class MeetingController {

    private MeetingService meetingService;

    private UserService userService;

    @Autowired
    public MeetingController(MeetingService meetingService, UserService userService) {
        this.meetingService = meetingService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("meetings", meetingService.getAllMeetings());
        return "meeting_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Long id){
        Meeting meeting = meetingService.getMeetingsById(id).orElseThrow(() -> new IllegalArgumentException("Meeting not found"));
        if(meeting != null){
            List<User> users = new ArrayList<>(meeting.getUsers());

            System.out.println("Meeting ID: " + meeting.getId());
            for (User user : meeting.getUsers()) {
                System.out.println("User: " + user.getUsername());
            }

            model.addAttribute("meeting", meeting);
            model.addAttribute("users", users);
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
        Optional<Meeting> meeting = meetingService.getMeetingsById(id);
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
    public String save(@Valid Meeting meeting, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "meeting_edit";
        }
        List<User> users = userService.findAll();
        meeting.setUsers(new HashSet<>(users));
        meetingService.saveMeeting(meeting);
        return "redirect:/meetings/";
    }
}
