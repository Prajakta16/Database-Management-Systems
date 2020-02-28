
DELIMITER //
CREATE PROCEDURE endorsedUsersForWeek (IN Date1 Date, IN Date2 Date)
BEGIN
	SELECT user.id AS user_id,p.first_name,q.id AS question_id,q.posted_on,COUNT(a.id) AS num_of_answers
	FROM user JOIN widget a ON user.id=a.posted_by 
    JOIN widget q ON q.id=a.question_widget_id 
    JOIN person p ON user.personid=p.id
	WHERE a.type='answer' AND a.correct_answer=1 AND q.type='question' 
			 -- AND (q.posted_on BETWEEN DATE_SUB(NOW(), INTERVAL 7 DAY) AND NOW())
              AND (q.posted_on BETWEEN Date1 AND Date2)
	GROUP BY user.id
    ORDER BY num_of_answers DESC,p.first_name ASC
    LIMIT 5;
END //