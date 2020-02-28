
-- Trigger for inserting priviledges for a new page_role
DELIMITER //
create 
	trigger after_insert_page_role 
    after insert on page_role 
    for each row 
    begin
		if(new.role='owner')
		then
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'create');
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'read');
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'update');
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'delete');
		elseif(new.role='admin')
		then
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'create');
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'read');
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'update');
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'delete');
		elseif(new.role='writer')
		then
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'create');
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'read');
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'update');
		elseif(new.role='editor')
		then
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'read');
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'update');
		else
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'read');
		END IF ;
	END //
delimiter ;

-- Trigger for updating priviledges for an updated page_role. 
-- Strategy followed is 1)Deletion of all priviledges. 2) Insertion of new priviledges
DELIMITER //
create 
	trigger after_update_page_role 
    after update on page_role 
    for each row 
    begin
		delete from page_priviledge where developerid=old.developerid and pageid=old.pageid;
		if(new.role='owner')
		then
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'create');
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'read');
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'update');
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'delete');
		elseif(new.role='admin')
		then
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'create');
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'read');
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'update');
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'delete');
		elseif(new.role='writer')
		then
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'create');
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'read');
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'update');
		elseif(new.role='editor')
		then
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'read');
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'update');
		else
			insert into page_priviledge(developerid,pageid,priviledge) values (new.developerid,new.pageid,'read');
		END IF ;
	END //
delimiter ;

-- Trigger for deleting priviledges during deletion of page_role. 
DELIMITER //
create 
	trigger before_delete_page_role 
    before delete on page_role 
    for each row 
    begin
		delete from page_priviledge where developerid=old.developerid and pageid=old.pageid;
	END //
delimiter ;

-- Trigger for inserting priviledges for a new website_role

DELIMITER //
create 
	trigger after_insert_website_role
    after insert on website_role 
    for each row 
    begin
		if(new.role='owner')
		then
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'create');
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'read');
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'update');
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'delete');
		elseif(new.role='admin')
		then
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'create');
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'read');
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'update');
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'delete');
		elseif(new.role='writer')
		then
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'create');
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'read');
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'update');
		elseif(new.role='editor')
		then
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'read');
			insert into website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'update');
		else
			insert into website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'read');
		END IF ;
	END //
delimiter ;


-- Trigger for updating priviledges for an updated website_role. 
-- Strategy followed is 1)Deletion of all priviledges. 2) Insertion of new priviledges

DELIMITER //
create 
	trigger after_update_website_role
    after update on website_role 
    for each row 
    begin
		delete from website_priviledge where developerid=old.developerid and websiteid=old.websiteid;
		if(new.role='owner')
		then
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'create');
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'read');
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'update');
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'delete');
		elseif(new.role='admin')
		then
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'create');
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'read');
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'update');
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'delete');
		elseif(new.role='writer')
		then
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'create');
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'read');
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'update');
		elseif(new.role='editor')
		then
			 insert into  website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'read');
			insert into website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'update');
		else
			insert into website_priviledge(developerid,websiteid,priviledge) values (new.developerid,new.websiteid,'read');
		END IF ;
	END //
delimiter ;


-- Trigger for deleting priviledges during deletion of website_role. 
DELIMITER //
create 
	trigger before_delete_website_role 
    before delete on website_role 
    for each row 
    begin
		delete from website_priviledge where developerid=old.developerid and websiteid=old.websiteid;
	END //
delimiter ;
