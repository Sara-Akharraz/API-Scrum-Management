package com.apiscrum.apiscrum.Service;

import com.apiscrum.apiscrum.DTO.EpicDto;
import com.apiscrum.apiscrum.DTO.UserStoryDto;
import com.apiscrum.apiscrum.Entity.Epic;
import com.apiscrum.apiscrum.Entity.ProductBackLog;
import com.apiscrum.apiscrum.Entity.UserStory;
import com.apiscrum.apiscrum.Mapper.UserStoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EpicService {
    public EpicDto addEpic(EpicDto epic, Long id_pb);
    public EpicDto getEpic(Long id);
    public EpicDto updateEpic(EpicDto epic, Long id);
    public void deleteEpic(Long id);
//    public void addUserStrotyToEpic(Long id_e, UserStory userStory);
    public List<UserStoryDto> getUserEpicStories(Long id);
    public List<EpicDto> getEpics();
    public EpicDto addUserStoryToEpic(UserStoryDto userStory, Long id);
}
