package via.doc1.devopsdemo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testDefaultConstructor() {
        Task task = new Task();
        assertNull(task.getId());
        assertNull(task.getName());
        assertNull(task.getDescription());
    }

    @Test
    void testParameterizedConstructorAndGetters() {
        Task task = new Task("1", "Task Name", "Task Description");
        
        assertEquals("1", task.getId());
        assertEquals("Task Name", task.getName());
        assertEquals("Task Description", task.getDescription());
    }

    @Test
    void testSetters() {
        Task task = new Task();
        task.setId("2");
        task.setName("New Name");
        task.setDescription("New Description");
        
        assertEquals("2", task.getId());
        assertEquals("New Name", task.getName());
        assertEquals("New Description", task.getDescription());
    }

    @Test
    void testToString() {
        Task task = new Task("3", "Task 3", "Desc 3");
        String expected = "Task{id='3', name='Task 3', description='Desc 3'}";
        assertEquals(expected, task.toString());
    }

    @Test
    void testEqualsSameObject() {
        Task task = new Task("1", "T1", "D1");
        assertTrue(task.equals(task));
    }

    @Test
    void testEqualsNull() {
        Task task = new Task("1", "T1", "D1");
        assertFalse(task.equals(null));
    }

    @Test
    void testEqualsDifferentClass() {
        Task task = new Task("1", "T1", "D1");
        assertFalse(task.equals(new Object()));
    }

    @Test
    void testEqualsDifferentIds() {
        Task task1 = new Task("1", "T1", "D1");
        Task task2 = new Task("2", "T2", "D2");
        assertFalse(task1.equals(task2));
        
        // Covering id is null cases if they exist. Based on equal structure it assumes id is not null.
        // It uses id.equals(task.id) directly so it would throw NullPointerException if id was null
    }

    @Test
    void testEqualsSameIds() {
        Task task1 = new Task("1", "T1", "D1");
        Task task2 = new Task("1", "Different Name", "Different Desc");
        assertTrue(task1.equals(task2));
    }

    @Test
    void testHashCode() {
        Task task1 = new Task("1", "T1", "D1");
        Task task2 = new Task("1", "T2", "D2");
        Task task3 = new Task("2", "T3", "D3");
        
        assertEquals(task1.hashCode(), task2.hashCode());
        assertNotEquals(task1.hashCode(), task3.hashCode());
    }
}
