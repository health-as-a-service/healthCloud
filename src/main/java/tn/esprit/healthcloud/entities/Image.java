package tn.esprit.healthcloud.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "images")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Image {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  
	private String path;

	@OneToOne(mappedBy = "image", cascade = {CascadeType.ALL})
    @JsonIgnore
    private Patient patient;
    
  

}
