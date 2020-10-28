package in.myapp.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Entity for storing/fetching Student info.
 * 
 * @author PranaySK
 */

@Data
@Entity
@Table(name = "student")
@ApiModel(description = "Details of Student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	@ApiModelProperty(notes = "Name should have atleat 2 characters.", required = true)
	private String name;

	@Column(name = "PASSPORTNUMBER")
	@ApiModelProperty(notes = "PassportNumber should have atleat 5 characters.", required = true)
	private String passportNumber;
}
