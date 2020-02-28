package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Page;

public interface RoleDao {

    void assignWebsiteRole(int developerId, int websiteId, int roleId);

    void assignPageRole(int developerId, int pageId, int roleId);

    void swapPageRole(Developer developer1, Developer developer2, Page page);

    void deleteWebsiteRole(int developerId, int websiteId, int roleId);

    void deletePageRole(int developerId, int pageId, int roleId);

}
