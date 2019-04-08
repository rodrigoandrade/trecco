package br.com.treccowebback.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * @author rodrigo.andrade
 *
 */

@ToString
@EqualsAndHashCode 
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
	
	public Member() {}

	public Member(@NonNull String name, @NonNull Boolean active) {
		super();
		this.name = name;
		this.active = active;
	}
	
}
