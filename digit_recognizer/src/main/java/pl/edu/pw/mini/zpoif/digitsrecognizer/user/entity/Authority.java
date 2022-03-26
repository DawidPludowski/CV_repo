package pl.edu.pw.mini.zpoif.digitsrecognizer.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authorities")
@Entity
public class Authority {
	
	@Id
    @JoinColumn(name="username", nullable=false)
	private String username;
	
	@Column(name = "authority")
	private String autority;
}
