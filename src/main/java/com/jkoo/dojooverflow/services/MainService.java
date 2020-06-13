package com.jkoo.dojooverflow.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkoo.dojooverflow.models.Answer;
import com.jkoo.dojooverflow.models.Question;
import com.jkoo.dojooverflow.models.Tag;
import com.jkoo.dojooverflow.repositories.AnswerRepository;
import com.jkoo.dojooverflow.repositories.QuestionRepository;
import com.jkoo.dojooverflow.repositories.TagRepository;

@Service
public class MainService {
	@Autowired
	private QuestionRepository qRepo;
	@Autowired
	private TagRepository tRepo;
	@Autowired
	private AnswerRepository aRepo;
	
	public List<Question> allQuestions() {
		return qRepo.findAll();
	}
	
	public List<Answer> allAnswersQ(Long questionID) {
		return aRepo.findByQuestion(questionID);
	}
	
	public Optional<Question> getQuestion(Long id) {
		
		return qRepo.findById(id);
	}
	
	public Question createQuestion(String question) {
		Question q = new Question(question);
		return qRepo.save(q);
	}
	
	public Question updateQuestion(Question question) {
		return qRepo.save(question);
	}
	
	public Answer createAnswer(String a, Question q) {
//		System.out.println(a.getQuestion().getId());
//		Optional<Question> checkq = qRepo.findById(a.getQuestion().getId());
//		if (checkq.isPresent()) {
//			System.out.println(checkq);
//			return aRepo.save(a);
//		}
//		return null;
		Answer answer = new Answer();
		answer.setAnswer(a);
		answer.setQuestion(q);
		return aRepo.save(answer);
	}
	
	public Optional<Tag> getTag(Long id) {
		return tRepo.findById(id);
	}
	
	public List<Tag> findTag(String s) {
		return tRepo.findBySubject(s);
	}
	
	public ArrayList<String> validateTag(String tags) {
		if (tags.length()==0) {
			System.out.println("string is empty");
			return null;
		}
//		List<String> tList = Arrays.asList(tags.split("\\s*,\\s*"));
		List<String> tList = Arrays.asList(tags.split(","));
		ArrayList<String> valList = new ArrayList<String>();
		if (tList.isEmpty() || tList.size() > 3) {
			System.out.println("list empty or too many tags");
			return null;
		}
		System.out.println(Arrays.toString(tList.toArray()));
		for (String i: tList) {
			String checktag = i.trim();
			if (checktag == "") {
				System.out.println("tag in list is empty");
				return null;
			} else {
				valList.add(checktag.toLowerCase());
			}
		}
		return valList;
	}
	
	public void createTag(List<String> tList) {
		for (String i: tList) {
			List<Tag> checklist = tRepo.findBySubject(i);
			if (checklist.isEmpty()) {
				Tag newt = new Tag(i);
				tRepo.save(newt);
			}
		}
		return;
		
	}
	
	public String validateAnswer(String answer) {
		if (answer.length() < 2) {
			return null;
		}
		return answer;
	}
	
	
}
