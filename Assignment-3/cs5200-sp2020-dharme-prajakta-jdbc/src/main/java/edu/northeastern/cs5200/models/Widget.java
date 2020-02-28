package edu.northeastern.cs5200.models;

import org.springframework.boot.context.event.SpringApplicationEvent;

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

    private int size;//heading
    private String html; //htlml
    private String src; //image
    private String url; //youTube
    private Boolean shareble;
    private Boolean expandable;

    public Widget(){

    }

    public Widget(int id, String name, int width, int height, String text, int widget_order,Type type, Page page, String url) {
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
