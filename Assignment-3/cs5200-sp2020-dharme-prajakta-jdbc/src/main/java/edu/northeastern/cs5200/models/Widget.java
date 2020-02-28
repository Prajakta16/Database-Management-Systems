package edu.northeastern.cs5200.models;

import org.springframework.boot.context.event.SpringApplicationEvent;

import java.sql.Date;

public class Widget {
    private int id;
    private String name;
    private int width;
    private int height;
    private String css_class;
    private String css_style;
    private String text;
    private int widget_order;
    private Type type;
    private Page page;

    private int posted_by; //User
    private Date posted_on;
    private boolean correct_answer;
    private int up_votes;
    private int down_votes;
    private int question_widget_id;

    private Module module;
    private int asked_by;
    private int length;
    private int views;
    private boolean endorsed_by_instructor;

    private int size;//heading
    private String html; //htlml
    private String src; //image
    private String url; //youTube
    private Boolean shareble;
    private Boolean expandable;

    public Widget(){

    }

    public Widget(int id,String name, String text, Type type, Page page, Date posted_on, Module module, int asked_by, int length, int views, boolean endorsed_by_instructor) {
        this.id=id;
        this.name = name;
        this.text = text;
        this.type = type;
        this.page = page;
        this.posted_on = posted_on;
        this.module = module;
        this.asked_by = asked_by;
        this.length = length;
        this.views = views;
        this.endorsed_by_instructor = endorsed_by_instructor;
    }

    public int getPosted_by() {
        return posted_by;
    }

    public void setPosted_by(int posted_by) {
        this.posted_by = posted_by;
    }

    public Date getPosted_on() {
        return posted_on;
    }

    public void setPosted_on(Date posted_on) {
        this.posted_on = posted_on;
    }

    public boolean isCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(boolean correct_answer) {
        this.correct_answer = correct_answer;
    }

    public int getUp_votes() {
        return up_votes;
    }

    public void setUp_votes(int up_votes) {
        this.up_votes = up_votes;
    }

    public int getDown_votes() {
        return down_votes;
    }

    public void setDown_votes(int down_votes) {
        this.down_votes = down_votes;
    }

    public int getQuestion_widget_id() {
        return question_widget_id;
    }

    public void setQuestion_widget_id(int question_widget_id) {
        this.question_widget_id = question_widget_id;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public int getAsked_by() {
        return asked_by;
    }

    public void setAsked_by(int asked_by) {
        this.asked_by = asked_by;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public boolean isEndorsed_by_instructor() {
        return endorsed_by_instructor;
    }

    public void setEndorsed_by_instructor(boolean endorsed_by_instructor) {
        this.endorsed_by_instructor = endorsed_by_instructor;
    }

    public Widget(String name, String text, Type type, int posted_by, Date posted_on, boolean correct_answer, int up_votes, int down_votes, int question_widget_id) {
        this.name = name;
        this.text = text;
        this.type = type;
        this.posted_by = posted_by;
        this.posted_on = posted_on;
        this.correct_answer = correct_answer;
        this.up_votes = up_votes;
        this.down_votes = down_votes;
        this.question_widget_id = question_widget_id;
    }

    public Widget(int id, String name, int width, int height, String text, int widget_order, Type type, Page page, String url) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.text = text;
        this.widget_order = widget_order;
        this.type = type;
        this.page = page;
        this.url = url;
    }

    public Widget(int id, String name, int width, int height, String css_class, String css_style, String text, int widget_order,Type type, Page page, String url) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.css_class = css_class;
        this.css_style = css_style;
        this.text = text;
        this.widget_order = widget_order;
        this.type = type;
        this.page = page;
        this.url = url;
    }

    public Widget(int id, String name, int width, int height, String css_class, String css_style, String text, int widget_order, Type type, int size, String html, String src, String url, Boolean shareble, Boolean expandable) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.css_class = css_class;
        this.css_style = css_style;
        this.text = text;
        this.widget_order = widget_order;
        this.type = type;
        this.size = size;
        this.html = html;
        this.src = src;
        this.url = url;
        this.shareble = shareble;
        this.expandable = expandable;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidget_order() {
        return widget_order;
    }

    public void setWidget_order(int order) {
        this.widget_order = order;
    }

    public String getCss_class() {
        return css_class;
    }

    public void setCss_class(String css_class) {
        this.css_class = css_class;
    }

    public String getCss_style() {
        return css_style;
    }

    public void setCss_style(String css_style) {
        this.css_style = css_style;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getShareble() {
        return shareble;
    }

    public void setShareble(Boolean shareble) {
        this.shareble = shareble;
    }

    public Boolean getExpandable() {
        return expandable;
    }

    public void setExpandable(Boolean expandable) {
        this.expandable = expandable;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }


}
