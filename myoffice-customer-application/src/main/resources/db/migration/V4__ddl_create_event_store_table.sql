CREATE TABLE myo_event_publication (
  pk_id           BIGINT(10)   NOT NULL AUTO_INCREMENT,
  event_name      VARCHAR(128) NOT NULL,
  event_timestamp TIMESTAMP    NOT NULL,
  event           LONGTEXT     NOT NULL,
  status          VARCHAR(10)  NOT NULL,
  timestamp       TIMESTAMP    NULL,
  PRIMARY KEY (pk_id)
);