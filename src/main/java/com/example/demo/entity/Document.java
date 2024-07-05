package com.example.demo.entity;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_document")
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String file; // Caminho do arquivo armazenado no sistema de arquivos
	private Date date;
	private Date dateSystem;
	private String category;

	@ManyToOne
	@JoinColumn(name = "producer_id")
	private Producer producer;

	// Getters e Setters

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

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDateSystem() {
		return dateSystem;
	}

	public void setDateSystem(Date dateSystem) {
		this.dateSystem = dateSystem;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

//	@Override
//	public int hashCode() {
//		return Objects.hash(category, date, dateSystem, file, id, producer, title);
//	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		return Objects.equals(category, other.category) && Objects.equals(date, other.date)
				&& Objects.equals(dateSystem, other.dateSystem) && Objects.equals(file, other.file)
				&& Objects.equals(id, other.id) && Objects.equals(producer, other.producer)
				&& Objects.equals(title, other.title);
	}
}
