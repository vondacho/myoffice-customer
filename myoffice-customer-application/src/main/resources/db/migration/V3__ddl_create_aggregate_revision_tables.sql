CREATE TABLE t_folder_aud (
  pk_id   BIGINT(10)   NOT NULL,
  id      VARCHAR(40)  NOT NULL,
  name    VARCHAR(10)  NOT NULL,
  notes   VARCHAR(255) NULL,
  rev     INTEGER      NOT NULL,
  revtype TINYINT,
  PRIMARY KEY (pk_id, rev)
);

CREATE TABLE t_folder_affiliate_aud (
  fk_folder      BIGINT(10)  NOT NULL,
  customer_id    VARCHAR(40) NOT NULL,
  primary_debtor BOOLEAN     NULL,
  rev            INTEGER     NOT NULL,
  revtype        TINYINT,
  PRIMARY KEY (fk_folder, customer_id, rev)
);

CREATE TABLE t_customer_aud (
  pk_id                 BIGINT(10)  NOT NULL,
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
  rev                   INTEGER     NOT NULL,
  revtype               TINYINT,
  PRIMARY KEY (pk_id, rev)
);

