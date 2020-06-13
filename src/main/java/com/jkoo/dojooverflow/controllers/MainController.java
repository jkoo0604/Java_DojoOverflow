package com.jkoo.dojooverflow.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jkoo.dojooverflow.models.Answer;
import com.jkoo.dojooverflow.models.Question;
import com.jkoo.dojooverflow.models.Tag;
import com.jkoo.dojooverflow.services.MainService;

@Controller
public class MainController {
	@Autowired
	private MainService mainService;
	
	@RequestMapping("/questions")
	public String index(Model model) {
		List<Question> q = mainService.allQuestions();
		model.addAttribute("questions", q);
		return "/questions/index.jsp";
	}
	
	@RequestMapping("/questions/new")
	public String newQuestion() {
		return "/questions/new.jsp";
	}
	
	@RequestMapping(value="/questions", method=RequestMethod.POST)
	public String createQuestion(@RequestParam("question") String question, @RequestParam("tags") String tags, RedirectAttributes error) {
		ArrayList<String> tagList = mainService.validateTag(tags);
		if (question.length() < 5) {
			error.addFlashAttribute("questionerr", "Question must be at least 5 characters in length");
			return "redirect:/questions/new";
		}
		if (tagList == null) {
			error.addFlashAttribute("tagerr", "You must enter up to three tags, separated by a comma");
			return "redirect:/questions/new";
		}
		Question newq = mainService.createQuestion(question);
		mainService.createTag(tagList);
		ArrayList<Tag> insertTag = new ArrayList<Tag>();
		for (String i: tagList) {
			List<Tag> checktag = mainService.findTag(i);
			if (!checktag.isEmpty()) {
				Tag t = checktag.get(0);
				insertTag.add(t);
				
			} else {
				System.out.println("no match for tag");
			}
		}
		newq.setTags(insertTag);
		mainService.updateQuestion(newq);
		
		return "redirect:/questions/" + newq.getId();
	}
	
	@RequestMapping("/questions/{id}")
	public String showQuestion(@PathVariable("id") Long id, Model model) {
		Optional<Question> checkq = mainService.getQuestion(id);
		if (checkq.isPresent()) {
			Question q = checkq.get();
			model.addAttribute("question", q);
			return "/questions/show.jsp";
		}
		return "redirect:/questions/";	
		
	}
	
	@RequestMapping(value="/questions/{id}/add", method=RequestMethod.POST)
	public String addAnswer(@PathVariable("id") Long questionID, @RequestParam("answer") String answer, Model model, RedirectAttributes error) {
		String checkanswer = mainService.validateAnswer(answer);
		if (checkanswer == null) {
			error.addFlashAttribute("answererr", "Answer must be at least 2 characters in lengeth");
			return "redirect:/questions/" + questionID;
		}
		Optional<Question> checkq = mainService.getQuestion(questionID);
		if (checkq.isPresent()) {
			Question q = checkq.get();
			mainService.createAnswer(answer, q);
			return "redirect:/questions/" + q.getId();
			
		}
		return "redirect:/questions/";			
		
	}
	
	@RequestMapping("/tags/{id}")
	public String showTag(@PathVariable("id") Long tagID, Model model) {
		Optional<Tag> checkt = mainService.getTag(tagID);
		if (checkt.isPresent()) {
			Tag tag = checkt.get();
			model.addAttribute("tag", tag);
			return "/tags/show.jsp";
		}
		return "redirect:/questions";
	}

}
