package cz.uhk.ppro.ppro.service;

import cz.uhk.ppro.ppro.model.Meeting;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MeetingService {
    List<Meeting> getAllMeetings();
    Meeting getMeetingsById(long id);
    void deleteMeetingById(long id);
    void saveMeeting(Meeting meeting);
}
