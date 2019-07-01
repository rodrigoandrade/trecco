package br.com.treccowebback.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

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
public class Member {
	
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
}
