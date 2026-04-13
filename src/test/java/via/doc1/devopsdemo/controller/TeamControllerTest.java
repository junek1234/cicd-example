package via.doc1.devopsdemo.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import via.doc1.devopsdemo.model.Task;
import via.doc1.devopsdemo.service.TeamService;
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = TeamController.class)
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    @Test
    void getTaskDetailsReturnsTaskJson() throws Exception {
    Task mockTask = new Task("Task1", "IoT Pipeline", "Create CD pipeline for IoT component");
    given(teamService.getTask("Member1", "Task1")).willReturn(mockTask);

    mockMvc.perform(get("/members/{memberId}/tasks/{taskId}", "Member1", "Task1")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value("Task1"))
        .andExpect(jsonPath("$.name").value("IoT Pipeline"))
        .andExpect(jsonPath("$.description").value("Create CD pipeline for IoT component"));

    verify(teamService).getTask(eq("Member1"), eq("Task1"));
    }

    @Test
    void getTaskDetailsReturnsEmptyBodyWhenTaskMissing() throws Exception {
    given(teamService.getTask("Member1", "UnknownTask")).willReturn(null);

    mockMvc.perform(get("/members/{memberId}/tasks/{taskId}", "Member1", "UnknownTask")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(""));

    verify(teamService).getTask(eq("Member1"), eq("UnknownTask"));
    }
}
