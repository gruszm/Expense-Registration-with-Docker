package com.gruszm.kontenery.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "expenses")
public class Expense
{
    @Id
    private String id;
    private LocalDateTime timestamp;
    private String name;
    private String description;
    private BigDecimal value;
    @DBRef
    private User user;
    @DBRef
    private Category category;

    public Expense()
    {

    }

    public Expense(String name, String description, double value, User user, Category category)
    {
        this.timestamp = LocalDateTime.now();
        this.name = name;
        this.description = description;
        this.value = BigDecimal.valueOf(value);
        this.user = user;
        this.category = category;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public BigDecimal getValue()
    {
        return value;
    }

    public void setValue(BigDecimal value)
    {
        this.value = value;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Category getCategory()
    {
        return category;
    }

    public String getCategoryName()
    {
        return category == null ? "null" : category.getName();
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    @Override
    public String toString()
    {
        return "Expense{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", user=" + user +
                ", category=" + category +
                '}';
    }
}
