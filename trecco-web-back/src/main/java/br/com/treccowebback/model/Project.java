package br.com.treccowebback.model;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.treccowebback.adapter.LocalDateTimeDeserializer;
import br.com.treccowebback.adapter.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * @author rodrigo.andrade
 *
 */

@Builder
@ToString
@EqualsAndHashCode 
@AllArgsConstructor
public class Project {
	
	@Id
	@Getter
	private ObjectId id;

	@Getter 
	@Setter 
	@NonNull
	@Indexed(unique = true)
	private String name;
	
	@Getter 
	@Setter 
	@NonNull
	private Boolean active;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Getter 
	@Setter
	private LocalDate initialDate;
	
	@Getter 
	@Setter
	private List<Member> members;
}
