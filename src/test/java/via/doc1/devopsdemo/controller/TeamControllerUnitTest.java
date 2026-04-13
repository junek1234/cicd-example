package via.doc1.devopsdemo.controller;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import via.doc1.devopsdemo.model.Task;
import via.doc1.devopsdemo.service.TeamService;

@ExtendWith(MockitoExtension.class)
class TeamControllerUnitTest {

    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;

    @Test
    void getTaskDetailsReturnsTaskFromService() {
        Task expectedTask = new Task("Task1", "IoT Pipeline", "Create CD pipeline for IoT component");
        when(teamService.getTask("Member1", "Task1")).thenReturn(expectedTask);

        Task actualTask = teamController.getTaskDetails("Member1", "Task1");

        assertSame(expectedTask, actualTask);
        verify(teamService).getTask("Member1", "Task1");
    }
}
