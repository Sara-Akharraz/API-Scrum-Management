package com.apiscrum.APIScrum.Service;

import com.apiscrum.APIScrum.DTO.EpicDto;
import com.apiscrum.APIScrum.DTO.UserStoryDto;
import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.ProductBackLog;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Mapper.UserStoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EpicService {
    public EpicDto addEpic(EpicDto epic, Long id_pb);
    public EpicDto getEpic(Long id);
    public void deleteEpic(Long id);
//    public void addUserStrotyToEpic(Long id_e, UserStory userStory);
    public List<UserStoryDto> getUserEpicStories(Long id);
    public List<EpicDto> getEpics();
    public EpicDto addUserStoryToEpic(UserStoryDto userStory, Long id);
}
