package com.apiscrum.APIScrum.Service.Impl;

import com.apiscrum.APIScrum.DTO.EpicDto;
import com.apiscrum.APIScrum.DTO.UserStoryDto;
import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.ProductBackLog;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Mapper.EpicMapper;
import com.apiscrum.APIScrum.Repository.EpicRepository;
import com.apiscrum.APIScrum.Repository.ProductBackLogRepository;
import com.apiscrum.APIScrum.Repository.UserStoryRepository;
import com.apiscrum.APIScrum.Service.EpicService;
import lombok.AllArgsConstructor;
import com.apiscrum.APIScrum.Mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EpicServiceImpl implements EpicService {

    @Autowired
    EpicRepository epicRepository;
    @Autowired
    ProductBackLogRepository productBackLogRepository;
    @Autowired
    UserStoryRepository userStoryRepository;
    @Override
    public EpicDto addEpic(EpicDto epic, Long id_pb) {
        ProductBackLog pb = productBackLogRepository.findById(id_pb)
                .orElseThrow(() -> new RuntimeException("Product Backlog not found with id: " + id_pb));
        Epic newEpic = Epic.builder()
                .title(epic.getTitle())
                .description(epic.getDescription())
                .productBackLog(pb).build();
        return EpicMapper.mapToEpicDTO(epicRepository.save(newEpic));
    }

    @Override
    public EpicDto getEpic(Long id) {
        Optional<Epic> epic = epicRepository.findById(id);
        if (epic.isPresent()) {
            return EpicMapper.mapToEpicDTO(epic.get());
        } else {
            throw new RuntimeException("Epic not found with id: " + id);
        }
    }

    @Override
    public EpicDto updateEpic(EpicDto epic, Long id) {
        Epic existingEpic = epicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Epic not found with id: " + id));
        existingEpic.setTitle(epic.getTitle());
        existingEpic.setDescription(epic.getDescription());
        existingEpic.setProductBackLog(epic.getProductBackLog());
        existingEpic.setUserStories(epic.getUserStories());
        Epic updatedEpic = epicRepository.save(existingEpic);
        return EpicMapper.mapToEpicDTO(epicRepository.save(updatedEpic));
    }

    @Override
    public void deleteEpic(Long id) {
        if(epicRepository.existsById(id))
            epicRepository.deleteById(id);
        else
            throw new RuntimeException("Epic not found with id: " + id);
    }

    @Override
    public List<UserStoryDto> getUserEpicStories(Long id) {
        Epic epic = epicRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Epic not found with id: " + id));

        List<UserStory> userStories = epic.getUserStories();
        return userStories.stream().map((userStory) -> UserStoryMapper.mapToUserStoryDTO(userStory))
                .collect(Collectors.toList());
    }

    @Override
    public List<EpicDto> getEpics() {
        List<Epic> epics = epicRepository.findAll();
        return epics.stream().map(EpicMapper::mapToEpicDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EpicDto addUserStoryToEpic(UserStoryDto userStory, Long id) {
        Epic epic = epicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Epic not found with id: " + id));
        userStory.setEpic(epic);
        userStoryRepository.save(UserStoryMapper.mapToUserStory(userStory));
        return EpicMapper.mapToEpicDTO(epic);
    }
}
