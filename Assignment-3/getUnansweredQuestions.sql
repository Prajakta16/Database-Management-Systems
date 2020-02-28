DELIMITER //
CREATE PROCEDURE getUnansweredQuestions()
BEGIN
	DROP VIEW IF EXISTS ANSWERS_PER_QUESTIONS;
	CREATE VIEW ANSWERS_PER_QUESTIONS AS
	(SELECT q.id AS question_id,q.module AS module,COUNT(a.id) AS num_of_answers
	FROM widget q JOIN widget a ON q.id=a.question_widget_id 
	WHERE q.type='question' AND a.type='answer' AND a.correct_answer=0 
	GROUP BY q.id) ;
	-- select * FROM Q4;

	SELECT question_id,module,MAX(num_of_answers) 
	FROM ANSWERS_PER_QUESTIONS
	GROUP BY module;
END //
 
DELIMITER ;
