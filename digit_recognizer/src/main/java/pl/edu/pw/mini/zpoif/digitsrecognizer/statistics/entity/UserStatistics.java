package pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_statistics")
public class UserStatistics {

	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "correct_guesses")
	private int correctGuesses;
	
	@Column(name = "all_guesses")
	private int allGuesses;
	
	public UserStatistics(String username) {
		this.username = username;
		this.correctGuesses = 0;
		this.allGuesses = 0;
	}
}
