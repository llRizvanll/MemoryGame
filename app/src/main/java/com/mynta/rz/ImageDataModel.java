package com.mynta.rz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rizvan.
 */

public class ImageDataModel {
    private String title;
    private String link;
    private String description;
    private String modified;
    private String generator;
    private List<Item> items = new ArrayList<Item>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The link
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * @param link
     * The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     *
     * @param modified
     * The modified
     */
    public void setModified(String modified) {
        this.modified = modified;
    }

    /**
     *
     * @return
     * The generator
     */
    public String getGenerator() {
        return generator;
    }

    /**
     *
     * @param generator
     * The generator
     */
    public void setGenerator(String generator) {
        this.generator = generator;
    }

    /**
     *
     * @return
     * The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     *
     * @param items
     * The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public class Item {

        private String title;
        private String link;
        private Media media;
        private String dateTaken;
        private String description;
        private String published;
        private String author;
        private String authorId;
        private String tags;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         *
         * @return
         * The title
         */
        public String getTitle() {
            return title;
        }

        /**
         *
         * @param title
         * The title
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         *
         * @return
         * The link
         */
        public String getLink() {
            return link;
        }

        /**
         *
         * @param link
         * The link
         */
        public void setLink(String link) {
            this.link = link;
        }

        /**
         *
         * @return
         * The media
         */
        public Media getMedia() {
            return media;
        }

        /**
         *
         * @param media
         * The media
         */
        public void setMedia(Media media) {
            this.media = media;
        }

        /**
         *
         * @return
         * The dateTaken
         */
        public String getDateTaken() {
            return dateTaken;
        }

        /**
         *
         * @param dateTaken
         * The date_taken
         */
        public void setDateTaken(String dateTaken) {
            this.dateTaken = dateTaken;
        }

        /**
         *
         * @return
         * The description
         */
        public String getDescription() {
            return description;
        }

        /**
         *
         * @param description
         * The description
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         *
         * @return
         * The published
         */
        public String getPublished() {
            return published;
        }

        /**
         *
         * @param published
         * The published
         */
        public void setPublished(String published) {
            this.published = published;
        }

        /**
         *
         * @return
         * The author
         */
        public String getAuthor() {
            return author;
        }

        /**
         *
         * @param author
         * The author
         */
        public void setAuthor(String author) {
            this.author = author;
        }

        /**
         *
         * @return
         * The authorId
         */
        public String getAuthorId() {
            return authorId;
        }

        /**
         *
         * @param authorId
         * The author_id
         */
        public void setAuthorId(String authorId) {
            this.authorId = authorId;
        }

        /**
         *
         * @return
         * The tags
         */
        public String getTags() {
            return tags;
        }

        /**
         *
         * @param tags
         * The tags
         */
        public void setTags(String tags) {
            this.tags = tags;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    public class Media {

        private String m;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         *
         * @return
         * The m
         */
        public String getM() {
            return m;
        }

        /**
         *
         * @param m
         * The m
         */
        public void setM(String m) {
            this.m = m;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

}
