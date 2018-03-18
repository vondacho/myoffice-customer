CREATE TABLE myo_folder (
  pk_id BIGINT(10)  NOT NULL AUTO_INCREMENT,
  id    VARCHAR(40) NOT NULL,
  name  VARCHAR(10) NOT NULL,
  notes LONGTEXT    NULL,
  PRIMARY KEY (pk_id),
  UNIQUE INDEX id_u (id)
);

CREATE TABLE myo_folder_affiliate (
  fk_folder      BIGINT(10)  NOT NULL,
  customer_id    VARCHAR(40) NOT NULL,
  primary_debtor BOOLEAN     NULL,
  INDEX fk_folder_i (fk_folder),
  FOREIGN KEY (fk_folder)
  REFERENCES myo_folder (pk_id)
);

CREATE TABLE myo_customer (
  pk_id                 BIGINT(10)  NOT NULL AUTO_INCREMENT,
  id                    VARCHAR(40) NOT NULL,
  salutation            VARCHAR(20) NULL,
  first_name            VARCHAR(30) NULL,
  last_name             VARCHAR(30) NOT NULL,
  birth_date            DATE        NULL,
  street_no             VARCHAR(60) NULL,
  zip                   VARCHAR(10) NOT NULL,
  city                  VARCHAR(40) NOT NULL,
  region                VARCHAR(40) NULL,
  country               VARCHAR(40) NOT NULL,
  email_address1        VARCHAR(80) NULL,
  email_kind1           VARCHAR(10) NULL,
  email_address2        VARCHAR(80) NULL,
  email_kind2           VARCHAR(10) NULL,
  email_address3        VARCHAR(80) NULL,
  email_kind3           VARCHAR(10) NULL,
  phone_number1         VARCHAR(80) NULL,
  phone_kind1           VARCHAR(10) NULL,
  phone_number2         VARCHAR(80) NULL,
  phone_kind2           VARCHAR(10) NULL,
  phone_number3         VARCHAR(80) NULL,
  phone_kind3           VARCHAR(10) NULL,
  phone_number4         VARCHAR(80) NULL,
  phone_kind4           VARCHAR(10) NULL,
  prof_has_moved        BOOLEAN     NULL,
  prof_is_subcontractor BOOLEAN     NULL,
  prof_send_invitation  BOOLEAN     NULL,
  soc_skype_url         VARCHAR(80) NULL,
  soc_facebook_url      VARCHAR(80) NULL,
  soc_googleplus_url    VARCHAR(80) NULL,
  soc_linkedin_url      VARCHAR(80) NULL,
  soc_twitter_url       VARCHAR(80) NULL,
  soc_instagram_url     VARCHAR(80) NULL,
  website_url           VARCHAR(80) NULL,
  notes                 LONGTEXT    NULL,
  PRIMARY KEY (pk_id),
  UNIQUE INDEX id_u (id)
);