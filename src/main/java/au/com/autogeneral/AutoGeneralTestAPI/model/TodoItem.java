package au.com.autogeneral.AutoGeneralTestAPI.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@ApiModel
public class TodoItem {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @ApiModelProperty(dataType="number", example="42")
    private Long id;

    @ApiModelProperty(dataType="string", example="Uulwi ifis halahs gag erh'ongg w'ssh.")
    private String text;

    @Column(updatable = false)
    @CreationTimestamp
    @ApiModelProperty(dataType="string", example="2017-10-13T01:50:58.735Z")
    private ZonedDateTime createdAt;

    @ApiModelProperty(dataType="boolean", example="false")
    private boolean isCompleted;

    public TodoItem() {
    }

    public TodoItem(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

}
