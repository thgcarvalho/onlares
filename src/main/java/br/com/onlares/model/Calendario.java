package br.com.onlares.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Entity
@Table(name = "calendario")
public class Calendario {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="titulo")
	private String title;
	@Column(name="inicio")
    private String start;
    @Column(name="fim")
    private String end;
    @Column(name="dia_todo")
    private boolean allDay;
    @Transient
    private String className;
    @Transient
    private String color;
	@ManyToOne
	@JoinColumn(name = "condominio_id", referencedColumnName = "id")
	private Condominio condominio;
	
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getStart() {
        return start;
    }
    public void setStart(String start) {
        this.start = start;
    }
    public String getEnd() {
        return end;
    }
    public void setEnd(String end) {
        this.end = end;
    }
	public boolean getAllDay() {
		return allDay;
	}
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public Condominio getCondominio() {
		return condominio;
	}
	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}
	
}