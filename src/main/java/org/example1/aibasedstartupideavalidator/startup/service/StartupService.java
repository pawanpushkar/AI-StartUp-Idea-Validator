package org.example1.aibasedstartupideavalidator.startup.service;

import lombok.RequiredArgsConstructor;
import org.example1.aibasedstartupideavalidator.common.exception.ResourceNotFoundException;
import org.example1.aibasedstartupideavalidator.startup.dto.CreateStartupRequest;
import org.example1.aibasedstartupideavalidator.startup.dto.StartupResponse;
import org.example1.aibasedstartupideavalidator.startup.dto.UpdateStartupRequest;
import org.example1.aibasedstartupideavalidator.startup.entity.StartupIdea;
import org.example1.aibasedstartupideavalidator.startup.repository.StartupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StartupService {

    private final StartupRepository startupRepository;

    // CREATE
    public StartupResponse createStartup(CreateStartupRequest request) {

        StartupIdea startupIdea = StartupIdea.builder()
                .startupName(request.getStartupName())
                .idea(request.getIdea())
                .problem(request.getProblem())
                .targetCustomer(request.getTargetCustomer())
                .market(request.getMarket())
                .businessModel(request.getBusinessModel())
                .build();

        StartupIdea savedStartup = startupRepository.save(startupIdea);

        return mapToResponse(savedStartup);
    }

    // GET ALL
    public List<StartupResponse> getAllStartups() {

        return startupRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // GET BY ID
    public StartupResponse getStartupById(Long id) {

        StartupIdea startup = startupRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Startup not found with id: " + id
                        )
                );

        return mapToResponse(startup);
    }

    // UPDATE
    public StartupResponse updateStartup(
            Long id,
            UpdateStartupRequest request) {

        StartupIdea startup = startupRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Startup not found with id: " + id
                        )
                );

        startup.setStartupName(request.getStartupName());
        startup.setIdea(request.getIdea());
        startup.setProblem(request.getProblem());
        startup.setTargetCustomer(request.getTargetCustomer());
        startup.setMarket(request.getMarket());
        startup.setBusinessModel(request.getBusinessModel());

        StartupIdea updatedStartup = startupRepository.save(startup);

        return mapToResponse(updatedStartup);
    }

    // DELETE
    public void deleteStartup(Long id) {

        if (!startupRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Startup not found with id: " + id
            );
        }

        startupRepository.deleteById(id);
    }

    // ENTITY -> RESPONSE DTO
    private StartupResponse mapToResponse(StartupIdea startup) {

        return StartupResponse.builder()
                .id(startup.getId())
                .startupName(startup.getStartupName())
                .idea(startup.getIdea())
                .problem(startup.getProblem())
                .targetCustomer(startup.getTargetCustomer())
                .market(startup.getMarket())
                .businessModel(startup.getBusinessModel())
                .createdAt(startup.getCreatedAt())
                .updatedAt(startup.getUpdatedAt())
                .build();
    }
}