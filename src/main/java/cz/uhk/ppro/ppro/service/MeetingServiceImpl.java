package cz.uhk.ppro.ppro.service;

import cz.uhk.ppro.ppro.model.Meeting;
import cz.uhk.ppro.ppro.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeetingServiceImpl implements MeetingService{
    MeetingRepository meetingRepository;

    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    @Override
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    @Override
    public Meeting getMeetingsById(Long id) {
        return meetingRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteMeetingById(Long id) {
        Optional<Meeting> meeting = meetingRepository.findById(id);
        if (meeting.isPresent()){
            meetingRepository.delete(meeting.get());
        }
    }

    @Override
    public void saveMeeting(Meeting meeting) {
        meetingRepository.save(meeting);
    }
}
