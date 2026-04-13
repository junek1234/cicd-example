package via.doc1.devopsdemo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;

import org.junit.jupiter.api.Test;

class TeamMemberTest {

    @Test
    void constructorAndGettersShouldReturnAssignedValues() {
        Task task = new Task("1", "Task One", "First task");
        List<Task> tasks = List.of(task);

        TeamMember member = new TeamMember("tm-1", "Alice", "alice@example.com", tasks);

        assertEquals("tm-1", member.getId());
        assertEquals("Alice", member.getName());
        assertEquals("alice@example.com", member.getEmail());
        assertSame(tasks, member.getTasks());
    }

    @Test
    void settersShouldUpdateAllFields() {
        TeamMember member = new TeamMember("old-id", "Old Name", "old@example.com", null);
        List<Task> updatedTasks = List.of(new Task("2", "Task Two", "Second task"));

        member.setId("new-id");
        member.setName("New Name");
        member.setEmail("new@example.com");
        member.setTasks(updatedTasks);

        assertEquals("new-id", member.getId());
        assertEquals("New Name", member.getName());
        assertEquals("new@example.com", member.getEmail());
        assertSame(updatedTasks, member.getTasks());
    }

    @Test
    void toStringShouldMatchClassFormat() {
        List<Task> tasks = List.of(new Task("3", "Task Three", "Third task"));
        TeamMember member = new TeamMember("tm-3", "Bob", "bob@example.com", tasks);

        String expected = "TeamMember [id=tm-3, name=Bob, email=bob@example.comtasks=[Task{id='3', name='Task Three', description='Third task'}]]";

        assertEquals(expected, member.toString());
    }
}
