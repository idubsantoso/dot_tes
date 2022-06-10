package com.mini.project.tes.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "log_table")
public class LogTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false,unique = true)
    private long id;

    @Column(name = "log_time")
    private Timestamp logTime;

    private String message;

    @Column(name = "event")
    private String event;

    @Column(name = "object")
    @Lob
    private String object;




    public static final class LogTableBuilder {
        private long id;
        private Timestamp logTime;
        private String message;
        private String event;
        private String object;

        private LogTableBuilder() {
        }

        public static LogTableBuilder aLogTable() {
            return new LogTableBuilder();
        }

        public LogTableBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public LogTableBuilder withLogTime(Timestamp logTime) {
            this.logTime = logTime;
            return this;
        }

        public LogTableBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public LogTableBuilder withEvent(String event) {
            this.event = event;
            return this;
        }

        public LogTableBuilder withObject(String object) {
            this.object = object;
            return this;
        }


        public LogTableEntity build() {
            LogTableEntity logTable = new LogTableEntity();
            logTable.setId(id);
            logTable.setLogTime(logTime);
            logTable.setMessage(message);
            logTable.setEvent(event);
            logTable.setObject(object);
            return logTable;
        }
    }

}
