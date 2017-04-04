--------------------------------------------------------
--  DDL for Table CALIBER_TRAINER
--------------------------------------------------------

  CREATE TABLE "CALIBER"."CALIBER_TRAINER" 
   (	"TRAINER_ID" NUMBER(10,0), 
	"EMAIL" VARCHAR2(255 CHAR), 
	"NAME" VARCHAR2(255 CHAR), 
	"TIER" VARCHAR2(255 CHAR), 
	"TITLE" VARCHAR2(255 CHAR)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index SYS_C0010597
--------------------------------------------------------

  CREATE UNIQUE INDEX "CALIBER"."SYS_C0010597" ON "CALIBER"."CALIBER_TRAINER" ("TRAINER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index UK_J0BB2CL63LSYCQ2N5AJ3LD22A
--------------------------------------------------------

  CREATE UNIQUE INDEX "CALIBER"."UK_J0BB2CL63LSYCQ2N5AJ3LD22A" ON "CALIBER"."CALIBER_TRAINER" ("EMAIL") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table CALIBER_TRAINER
--------------------------------------------------------

  ALTER TABLE "CALIBER"."CALIBER_TRAINER" ADD CONSTRAINT "UK_J0BB2CL63LSYCQ2N5AJ3LD22A" UNIQUE ("EMAIL")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "CALIBER"."CALIBER_TRAINER" ADD PRIMARY KEY ("TRAINER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "CALIBER"."CALIBER_TRAINER" MODIFY ("TITLE" NOT NULL ENABLE);
  ALTER TABLE "CALIBER"."CALIBER_TRAINER" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "CALIBER"."CALIBER_TRAINER" MODIFY ("EMAIL" NOT NULL ENABLE);
  ALTER TABLE "CALIBER"."CALIBER_TRAINER" MODIFY ("TRAINER_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  DDL for Table CALIBER_BATCH
--------------------------------------------------------

  CREATE TABLE "CALIBER"."CALIBER_BATCH" 
   (	"BATCH_ID" NUMBER(10,0), 
	"BENCHMARK_START_DATE" DATE, 
	"BORDERLINE_GRADE_THRESHOLD" NUMBER(5,0), 
	"END_DATE" DATE, 
	"GOOD_GRADE_THRESHOLD" NUMBER(5,0), 
	"LOCATION" VARCHAR2(255 CHAR), 
	"SKILL_TYPE" VARCHAR2(255 CHAR), 
	"START_DATE" DATE, 
	"TRAINING_NAME" VARCHAR2(255 CHAR), 
	"TRAINING_TYPE" VARCHAR2(255 CHAR), 
	"NUMBER_OF_WEEKS" NUMBER(10,0), 
	"CO_TRAINER_ID" NUMBER(10,0), 
	"TRAINER_ID" NUMBER(10,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index SYS_C0010578
--------------------------------------------------------

  CREATE UNIQUE INDEX "CALIBER"."SYS_C0010578" ON "CALIBER"."CALIBER_BATCH" ("BATCH_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table CALIBER_BATCH
--------------------------------------------------------

  ALTER TABLE "CALIBER"."CALIBER_BATCH" ADD PRIMARY KEY ("BATCH_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "CALIBER"."CALIBER_BATCH" MODIFY ("TRAINER_ID" NOT NULL ENABLE);
  ALTER TABLE "CALIBER"."CALIBER_BATCH" MODIFY ("NUMBER_OF_WEEKS" NOT NULL ENABLE);
  ALTER TABLE "CALIBER"."CALIBER_BATCH" MODIFY ("START_DATE" NOT NULL ENABLE);
  ALTER TABLE "CALIBER"."CALIBER_BATCH" MODIFY ("LOCATION" NOT NULL ENABLE);
  ALTER TABLE "CALIBER"."CALIBER_BATCH" MODIFY ("END_DATE" NOT NULL ENABLE);
  ALTER TABLE "CALIBER"."CALIBER_BATCH" MODIFY ("BENCHMARK_START_DATE" NOT NULL ENABLE);
  ALTER TABLE "CALIBER"."CALIBER_BATCH" MODIFY ("BATCH_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table CALIBER_BATCH
--------------------------------------------------------

  ALTER TABLE "CALIBER"."CALIBER_BATCH" ADD CONSTRAINT "FK_35QJTOMOM338V680F3XKO7PLH" FOREIGN KEY ("TRAINER_ID")
	  REFERENCES "CALIBER"."CALIBER_TRAINER" ("TRAINER_ID") ENABLE;
  ALTER TABLE "CALIBER"."CALIBER_BATCH" ADD CONSTRAINT "FK_4IDDE4FODT9VMU6CIXCQ4S2P1" FOREIGN KEY ("CO_TRAINER_ID")
	  REFERENCES "CALIBER"."CALIBER_TRAINER" ("TRAINER_ID") ENABLE;
--------------------------------------------------------
--  DDL for Table CALIBER_CATEGORY
--------------------------------------------------------

  CREATE TABLE "CALIBER"."CALIBER_CATEGORY" 
   (	"CATEGORY_ID" NUMBER(10,0), 
	"SKILL_CATEGORY" VARCHAR2(255 CHAR)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index SYS_C0010580
--------------------------------------------------------

  CREATE UNIQUE INDEX "CALIBER"."SYS_C0010580" ON "CALIBER"."CALIBER_CATEGORY" ("CATEGORY_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table CALIBER_CATEGORY
--------------------------------------------------------

  ALTER TABLE "CALIBER"."CALIBER_CATEGORY" ADD PRIMARY KEY ("CATEGORY_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "CALIBER"."CALIBER_CATEGORY" MODIFY ("CATEGORY_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  DDL for Table CALIBER_ASSESSMENT
--------------------------------------------------------

  CREATE TABLE "CALIBER"."CALIBER_ASSESSMENT" 
   (	"ASSESSMENT_ID" NUMBER(19,0), 
	"RAW_SCORE" NUMBER(10,0), 
	"ASSESSMENT_TITLE" VARCHAR2(255 CHAR) NOT NULL, 
	"ASSESSMENT_TYPE" VARCHAR2(255 CHAR) NOT NULL, 
	"WEEK_NUMBER" NUMBER(5,0) NOT NULL, 
	"BATCH_ID" NUMBER(10,0) NOT NULL, 
	"ASSESSMENT_CATEGORY" NUMBER(10,0) NOT NULL
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index SYS_C0010570
--------------------------------------------------------

  CREATE UNIQUE INDEX "CALIBER"."SYS_C0010570" ON "CALIBER"."CALIBER_ASSESSMENT" ("ASSESSMENT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table CALIBER_ASSESSMENT
--------------------------------------------------------

  ALTER TABLE "CALIBER"."CALIBER_ASSESSMENT" ADD PRIMARY KEY ("ASSESSMENT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "CALIBER"."CALIBER_ASSESSMENT" MODIFY ("BATCH_ID" NOT NULL ENABLE);
  ALTER TABLE "CALIBER"."CALIBER_ASSESSMENT" MODIFY ("ASSESSMENT_TYPE" NOT NULL ENABLE);
  ALTER TABLE "CALIBER"."CALIBER_ASSESSMENT" MODIFY ("ASSESSMENT_TITLE" NOT NULL ENABLE);
  ALTER TABLE "CALIBER"."CALIBER_ASSESSMENT" MODIFY ("ASSESSMENT_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table CALIBER_ASSESSMENT
--------------------------------------------------------

  ALTER TABLE "CALIBER"."CALIBER_ASSESSMENT" ADD CONSTRAINT "FK_3NBC8UK6IAM68O6DK4XVMRWL2" FOREIGN KEY ("ASSESSMENT_CATEGORY")
	  REFERENCES "CALIBER"."CALIBER_CATEGORY" ("CATEGORY_ID") ENABLE;
  ALTER TABLE "CALIBER"."CALIBER_ASSESSMENT" ADD CONSTRAINT "FK_83R77WO51CP74YVUFSLYP3E1M" FOREIGN KEY ("BATCH_ID")
	  REFERENCES "CALIBER"."CALIBER_BATCH" ("BATCH_ID") ENABLE;
--------------------------------------------------------
--  DDL for Table CALIBER_TRAINEE
--------------------------------------------------------

  CREATE TABLE "CALIBER"."CALIBER_TRAINEE" 
   (	"TRAINEE_ID" NUMBER(10,0), 
	"TRAINEE_EMAIL" VARCHAR2(255 CHAR), 
	"TRAINEE_NAME" VARCHAR2(255 CHAR), 
	"TRAINING_STATUS" VARCHAR2(255 CHAR), 
	"BATCH_ID" NUMBER(10,0), 
	"PHONE_NUMBER" VARCHAR2(255 CHAR), 
	"PROFILE_URL" VARCHAR2(255 CHAR), 
	"SKYPE_ID" VARCHAR2(255 CHAR)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index SYS_C0010592
--------------------------------------------------------

  CREATE UNIQUE INDEX "CALIBER"."SYS_C0010592" ON "CALIBER"."CALIBER_TRAINEE" ("TRAINEE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table CALIBER_TRAINEE
--------------------------------------------------------

  ALTER TABLE "CALIBER"."CALIBER_TRAINEE" ADD PRIMARY KEY ("TRAINEE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "CALIBER"."CALIBER_TRAINEE" MODIFY ("BATCH_ID" NOT NULL ENABLE);
  ALTER TABLE "CALIBER"."CALIBER_TRAINEE" MODIFY ("TRAINEE_ID" NOT NULL ENABLE);
  ALTER TABLE "CALIBER"."CALIBER_TRAINEE" MODIFY ("TRAINEE_EMAIL" NOT NULL ENABLE);
  alter table "CALIBER"."CALIBER_TRAINEE" add constraint TRAINEE_UNIQUE_EMAIL unique("TRAINEE_EMAIL") ;
--------------------------------------------------------
--  Ref Constraints for Table CALIBER_TRAINEE
--------------------------------------------------------

  ALTER TABLE "CALIBER"."CALIBER_TRAINEE" ADD CONSTRAINT "FK_I6XQ8I1717FWXU7UQBXHMGOWC" FOREIGN KEY ("BATCH_ID")
	  REFERENCES "CALIBER"."CALIBER_BATCH" ("BATCH_ID") ENABLE;
--------------------------------------------------------
--  DDL for Table CALIBER_GRADE
--------------------------------------------------------

  CREATE TABLE "CALIBER"."CALIBER_GRADE" 
   (	"GRADE_ID" NUMBER(19,0), 
	"DATE_RECEIVED" TIMESTAMP (6), 
	"SCORE" FLOAT(126), 
	"ASSESSMENT_ID" NUMBER(19,0), 
	"TRAINEE_ID" NUMBER(10,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index SYS_C0010586
--------------------------------------------------------

  CREATE UNIQUE INDEX "CALIBER"."SYS_C0010586" ON "CALIBER"."CALIBER_GRADE" ("GRADE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table CALIBER_GRADE
--------------------------------------------------------

  ALTER TABLE "CALIBER"."CALIBER_GRADE" ADD PRIMARY KEY ("GRADE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "CALIBER"."CALIBER_GRADE" MODIFY ("TRAINEE_ID" NOT NULL ENABLE);
  ALTER TABLE "CALIBER"."CALIBER_GRADE" MODIFY ("ASSESSMENT_ID" NOT NULL ENABLE);
  ALTER TABLE "CALIBER"."CALIBER_GRADE" MODIFY ("SCORE" NOT NULL ENABLE);
  ALTER TABLE "CALIBER"."CALIBER_GRADE" MODIFY ("DATE_RECEIVED" NOT NULL ENABLE);
  ALTER TABLE "CALIBER"."CALIBER_GRADE" MODIFY ("GRADE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table CALIBER_GRADE
--------------------------------------------------------

  ALTER TABLE "CALIBER"."CALIBER_GRADE" ADD CONSTRAINT "FK_7GF9T26KCY85A3XMQLA8TF4R1" FOREIGN KEY ("TRAINEE_ID")
	  REFERENCES "CALIBER"."CALIBER_TRAINEE" ("TRAINEE_ID") ENABLE;
  ALTER TABLE "CALIBER"."CALIBER_GRADE" ADD CONSTRAINT "FK_JCQEJLMKF8JA574MPH22YL483" FOREIGN KEY ("ASSESSMENT_ID")
	  REFERENCES "CALIBER"."CALIBER_ASSESSMENT" ("ASSESSMENT_ID") ENABLE;
--------------------------------------------------------
--  DDL for Table CALIBER_NOTE
--------------------------------------------------------

  CREATE TABLE "CALIBER"."CALIBER_NOTE" 
   (	"NOTE_ID" NUMBER(10,0), 
	"NOTE_CONTENT" VARCHAR2(255 CHAR), 
	"MAX_VISIBILITY" NUMBER(10,0), 
	"IS_QC_FEEDBACK" NUMBER(1,0), 
	"QC_STATUS" VARCHAR2(255 CHAR), 
	"NOTE_TYPE" VARCHAR2(255 CHAR), 
	"WEEK_NUMBER" NUMBER(5,0), 
	"BATCH_ID" NUMBER(10,0), 
	"TRAINEE_ID" NUMBER(10,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index SYS_C0010589
--------------------------------------------------------

  CREATE UNIQUE INDEX "CALIBER"."SYS_C0010589" ON "CALIBER"."CALIBER_NOTE" ("NOTE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table CALIBER_NOTE
--------------------------------------------------------

  ALTER TABLE "CALIBER"."CALIBER_NOTE" ADD PRIMARY KEY ("NOTE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "CALIBER"."CALIBER_NOTE" MODIFY ("IS_QC_FEEDBACK" NOT NULL ENABLE);
  ALTER TABLE "CALIBER"."CALIBER_NOTE" MODIFY ("NOTE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table CALIBER_NOTE
--------------------------------------------------------

  ALTER TABLE "CALIBER"."CALIBER_NOTE" ADD CONSTRAINT "FK_I8GMTI8EP44N7VE6KB1JK5CR3" FOREIGN KEY ("BATCH_ID")
	  REFERENCES "CALIBER"."CALIBER_BATCH" ("BATCH_ID") ENABLE;
  ALTER TABLE "CALIBER"."CALIBER_NOTE" ADD CONSTRAINT "FK_QU8BU6BS63XBDT5OUTD7VD4DJ" FOREIGN KEY ("TRAINEE_ID")
	  REFERENCES "CALIBER"."CALIBER_TRAINEE" ("TRAINEE_ID") ENABLE;
--------------------------------------------------------
--  DDL for Sequence ASSESSMENT_ID_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "CALIBER"."ASSESSMENT_ID_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence BATCH_ID_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "CALIBER"."BATCH_ID_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence CATEGORY_ID_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "CALIBER"."CATEGORY_ID_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence GRADE_ID_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "CALIBER"."GRADE_ID_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence NOTE_ID_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "CALIBER"."NOTE_ID_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE;
--------------------------------------------------------
--  DDL for Sequence TRAINEE_ID_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "CALIBER"."TRAINEE_ID_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence TRAINER_ID_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "CALIBER"."TRAINER_ID_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;

-------------TRAINER-------------
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'sadat.t.ahmed@gmail.com', 'Sadat Ahmed', 'Vice President of Technology', 'VP');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'ateebtahir@gmail.com', 'Ateeb Khawaja', 'Head of the Quality Department', 'QC');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'pjw6193@hotmail.com', 'Patrick Walsh', 'Senior Trainer', 'TRAINER');
INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'igluskin94@gmail.com', 'Igor Gluskin', 'Junior Trainer', 'TRAINER');
  INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'denise.j.montesdeoca@gmail.com', 'Denise Montesdeoca', 'Junior Trainer', 'TRAINER');
-------------TRAINER END-------------
-------------CATEGORY (TRAINING MODULE)----------------
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'Java');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'SQL');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'Servlet');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'JSP');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'XML');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'Git');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'JUnit');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'Maven');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'JDBC');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'HTML');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'CSS');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'JavaScript');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'jQuery');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'AJAX');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'UNIX');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'AWS');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'Jenkins');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'Hibernate');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL,'Spring');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'SOAP');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'REST');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'AngularJS');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'Selenium');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'Cucumber');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'UFT');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'Python');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'Robot Framework');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'ALM');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'SDLC');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'Agile');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'Testing');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'VBScript');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'SOAP UI');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'TestNG');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'Chef');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'Docker');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'Kafka');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'Microservices');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'NoSQL');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'C#');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'ASP.NET');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'ADO.NET');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'Entity Framework');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'MSBuild');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'WPF');
INSERT INTO CALIBER_CATEGORY VALUES (CATEGORY_ID_SEQUENCE.NEXTVAL, 'WCF');
-------------CATEGORY (TRAINING MODULE) END----------------
INSERT INTO CALIBER_BATCH(
  BATCH_ID,
	BENCHMARK_START_DATE
	,BORDERLINE_GRADE_THRESHOLD 
	,END_DATE
	,GOOD_GRADE_THRESHOLD
	,LOCATION
	,SKILL_TYPE
	,START_DATE
	,TRAINING_NAME 
	,TRAINING_TYPE
	,NUMBER_OF_WEEKS
	,CO_TRAINER_ID 
,TRAINER_ID
)VALUES(
49,
'01-JAN-2003', 
50, '16-JUL-2017', 80, 
'11730 Plaza America Drive Reston, VA 20190',
'J2EE', '02-APR-2017', '1701 Java DevOps', 'Revature', 1, 3, 5
);
INSERT INTO CALIBER_TRAINEE(
  TRAINEE_ID
	,TRAINEE_EMAIL 
	,TRAINEE_NAME 
	,TRAINING_STATUS 
	,BATCH_ID
	,PHONE_NUMBER
)
VALUES(49, 'dan@pickles.com', 'Dan Pickles','Training', 49, '(931) 993-0991');
INSERT INTO CALIBER_TRAINEE(
  TRAINEE_ID
	,TRAINEE_EMAIL 
	,TRAINEE_NAME 
	,TRAINING_STATUS 
	,BATCH_ID
	,PHONE_NUMBER
)
VALUES(47, 'randolph@scott.com', 'Randolph Scott','Training', 49, '(931) 993-1244');
INSERT INTO CALIBER_TRAINEE(
  TRAINEE_ID
	,TRAINEE_EMAIL 
	,TRAINEE_NAME 
	,TRAINING_STATUS 
	,BATCH_ID
	,PHONE_NUMBER
)VALUES(47, 'howard@johnson.com', 'Howard Johnson','Training', 49, '(931) 993-1664');
COMMIT;
  INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, TITLE, TIER)
  VALUES(
  CALIBER.TRAINER_ID_SEQUENCE.NEXTVAL, 'connelsp@gmail.com',
  'Sean Connelly', 'Junior Trainer', 'TRAINER');
INSERT INTO CALIBER_BATCH(
  BATCH_ID,
	BENCHMARK_START_DATE
	,BORDERLINE_GRADE_THRESHOLD 
	,END_DATE
	,GOOD_GRADE_THRESHOLD
	,LOCATION
	,SKILL_TYPE
	,START_DATE
	,TRAINING_NAME 
	,TRAINING_TYPE
	,NUMBER_OF_WEEKS
	,CO_TRAINER_ID 
,TRAINER_ID
)VALUES(
48,
'01-JAN-2003', 
50, '16-JUL-2017', 80, 
'11730 Plaza America Drive Reston, VA 20190',
'J2EE', '02-APR-2017', '1702 Java DevOps', 'Revature', 1, 3, 21
);
commit;
INSERT INTO CALIBER_TRAINEE(
  TRAINEE_ID
	,TRAINEE_EMAIL 
	,TRAINEE_NAME 
	,TRAINING_STATUS 
	,BATCH_ID
	,PHONE_NUMBER
)
VALUES(46, 'ron@gmail.com', 'Ron Howard','Training', 48, '(931) 993-0991');
INSERT INTO CALIBER_TRAINEE(
  TRAINEE_ID
	,TRAINEE_EMAIL 
	,TRAINEE_NAME 
	,TRAINING_STATUS 
	,BATCH_ID
	,PHONE_NUMBER
)
VALUES(45, 'randolph@scott.com', 'Randolph Scott','Training', 48, '(931) 993-4554');
INSERT INTO CALIBER_TRAINEE(
  TRAINEE_ID
	,TRAINEE_EMAIL 
	,TRAINEE_NAME 
	,TRAINING_STATUS 
	,BATCH_ID
	,PHONE_NUMBER
)
VALUES(44, 'howard@johnson.com', 'Howard Johnson','Training', 48, '(931) 993-1222');
COMMIT;

