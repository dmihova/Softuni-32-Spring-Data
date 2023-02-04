package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tasks")
@XmlAccessorType(XmlAccessType.FIELD)
public class TasksWrapperXMLDTO {

    @XmlElement(name = "task")
    private List<TaskImportXMLDTO> tasks;

    public TasksWrapperXMLDTO() {
    }

    public List<TaskImportXMLDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskImportXMLDTO> tasks) {
        this.tasks = tasks;
    }
}
