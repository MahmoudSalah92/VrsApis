
CREATE TABLE "VRS"."VRS_ROLES"
(	"ROLE_ID" NUMBER(2,0) NOT NULL ENABLE,
     "ROLE_DESC" VARCHAR2(200 BYTE) NOT NULL ENABLE,
     "IS_ACTIVE" NUMBER DEFAULT 1 NOT NULL ENABLE,
     CONSTRAINT "VRS_REQUEST_ROLES_PK" PRIMARY KEY ("ROLE_ID")
         USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
         STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
         PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
         BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
         TABLESPACE "USERS"  ENABLE
) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

COMMENT ON COLUMN "VRS"."VRS_ROLES"."ROLE_ID" IS 'كود الدور الوظيفي';
COMMENT ON COLUMN "VRS"."VRS_ROLES"."ROLE_DESC" IS 'اسم الدور الوظيفي';
COMMENT ON COLUMN "VRS"."VRS_ROLES"."IS_ACTIVE" IS 'الحالة (1 فعال 0 غير فعال)';

-----------------------------------------------------------------------------------------------------------------------

CREATE TABLE "VRS"."VRS_PHASES"
(	"PHASE_ID" NUMBER(3,0) NOT NULL ENABLE,
     "PHASE_NAME" VARCHAR2(500 BYTE) NOT NULL ENABLE,
     "PHASE_DESC" VARCHAR2(500 BYTE),
     "FROM_ROLE_NO" NUMBER(2,0) NOT NULL ENABLE,
     "TO_ROLE_NO" NUMBER(2,0) NOT NULL ENABLE,
     "BENEFICIARY_MESSAGE" VARCHAR2(2000 BYTE),
     "IS_ACTIVE" NUMBER(1,0) DEFAULT 1,
     "NOTES" VARCHAR2(200 BYTE),
     CONSTRAINT "VRS_PHASES_PK" PRIMARY KEY ("PHASE_ID")
         USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
         STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
         PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
         BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
         TABLESPACE "USERS"  ENABLE
) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

COMMENT ON COLUMN "VRS"."VRS_PHASES"."PHASE_ID" IS 'مسلسل او رقم المرحلة';
COMMENT ON COLUMN "VRS"."VRS_PHASES"."PHASE_NAME" IS 'اسم المرحلة';
COMMENT ON COLUMN "VRS"."VRS_PHASES"."PHASE_DESC" IS 'وصف المرحلة';
COMMENT ON COLUMN "VRS"."VRS_PHASES"."FROM_ROLE_NO" IS 'الدور الوظيفي للمرحلة الحالية';
COMMENT ON COLUMN "VRS"."VRS_PHASES"."TO_ROLE_NO" IS 'الدور الوظيفي التالي ';
COMMENT ON COLUMN "VRS"."VRS_PHASES"."BENEFICIARY_MESSAGE" IS 'رسالة اشعار المستفيد باجراء المرحلة';
COMMENT ON COLUMN "VRS"."VRS_PHASES"."IS_ACTIVE" IS 'حالة المرحلة (1 فعال  0 غير فعال)';
COMMENT ON COLUMN "VRS"."VRS_PHASES"."NOTES" IS 'ملاحظات المرحلة';

-----------------------------------------------------------------------------------------------------------------------

CREATE TABLE "VRS"."VRS_PHASE_ACTIONS"
(	"PHASE_ACTION_ID" NUMBER NOT NULL ENABLE,
     "PARENT_PHASE_ID" NUMBER NOT NULL ENABLE,
     "CHILD_PHASE_ID" NUMBER NOT NULL ENABLE,
     "IS_ACTIVE" NUMBER DEFAULT 1,
     "ACTION_ORDER" NUMBER,
     "NOTES" VARCHAR2(4000 BYTE),
     CONSTRAINT "VRS_PHASE_ACTIONS_PK" PRIMARY KEY ("PHASE_ACTION_ID")
         USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
         STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
         PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
         BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
         TABLESPACE "USERS"  ENABLE
) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

COMMENT ON COLUMN "VRS"."VRS_PHASE_ACTIONS"."PHASE_ACTION_ID" IS 'مسلسل اجراء المرحلة';
COMMENT ON COLUMN "VRS"."VRS_PHASE_ACTIONS"."PARENT_PHASE_ID" IS 'رقم المرحلة الاساسية';
COMMENT ON COLUMN "VRS"."VRS_PHASE_ACTIONS"."CHILD_PHASE_ID" IS 'رقم المرحلة الفرعية';
COMMENT ON COLUMN "VRS"."VRS_PHASE_ACTIONS"."IS_ACTIVE" IS 'فعال 1 غير فعال 2';
COMMENT ON COLUMN "VRS"."VRS_PHASE_ACTIONS"."ACTION_ORDER" IS 'ترتيب المرحلة الفرعية';
COMMENT ON COLUMN "VRS"."VRS_PHASE_ACTIONS"."NOTES" IS 'الملاحظات';

-----------------------------------------------------------------------------------------------------------------------

CREATE TABLE "VRS"."VRS_REQUESTS"
(	"REQUEST_ID" NUMBER NOT NULL ENABLE,
     "REQUEST_DATE" DATE DEFAULT sysdate,
     "START_DATE" DATE,
     "END_DATE" DATE,
     "DIR_TYPE" NUMBER,
     "DIR_CODE" NUMBER,
     "TRANSACTION_DETAILS" VARCHAR2(2000 BYTE),
     "NOTES" VARCHAR2(2000 BYTE),
     "DIR_EMPLOYEE_CODE" VARCHAR2(20 BYTE),
     "DIR_EMPLOYEE_STATEMENT" VARCHAR2(2000 BYTE),
     "DIR_MANAGER_VISIONS" VARCHAR2(2000 BYTE),
     "FINAL_APPROVER_VISIONS" VARCHAR2(2000 BYTE),
     "DIR_EMPLOYEE_STATEMENT_CONFIRM" VARCHAR2(2000 BYTE),
     "TRANSACTION_NO" NUMBER,
     "REQUEST_PHASE_ID" NUMBER,
     "CREATED_USER" VARCHAR2(20 BYTE),
     CONSTRAINT "VRS_REQUESTS_PK" PRIMARY KEY ("REQUEST_ID")
         USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
         STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
         PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
         BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
         TABLESPACE "USERS"  ENABLE
) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

COMMENT ON COLUMN "VRS"."VRS_REQUESTS"."REQUEST_ID" IS 'رقم الطلب';
COMMENT ON COLUMN "VRS"."VRS_REQUESTS"."REQUEST_DATE" IS 'تاريخ تسجيل الطلب';
COMMENT ON COLUMN "VRS"."VRS_REQUESTS"."START_DATE" IS 'تاريخ بداية الطلب';
COMMENT ON COLUMN "VRS"."VRS_REQUESTS"."END_DATE" IS 'تاريخ نهاية الطلب';
COMMENT ON COLUMN "VRS"."VRS_REQUESTS"."DIR_TYPE" IS 'نوع الادارة';
COMMENT ON COLUMN "VRS"."VRS_REQUESTS"."DIR_CODE" IS 'كود الادارة';
COMMENT ON COLUMN "VRS"."VRS_REQUESTS"."TRANSACTION_DETAILS" IS 'تفاصيل المعاملة بالاتصالات الادارية';
COMMENT ON COLUMN "VRS"."VRS_REQUESTS"."NOTES" IS 'الملاحظات';
COMMENT ON COLUMN "VRS"."VRS_REQUESTS"."DIR_EMPLOYEE_CODE" IS 'كود موظف الجهة';
COMMENT ON COLUMN "VRS"."VRS_REQUESTS"."DIR_EMPLOYEE_STATEMENT" IS 'مرئيات موظف الجهة';
COMMENT ON COLUMN "VRS"."VRS_REQUESTS"."DIR_MANAGER_VISIONS" IS 'مرئيات المدير المباشر';
COMMENT ON COLUMN "VRS"."VRS_REQUESTS"."FINAL_APPROVER_VISIONS" IS 'مرئيات المعتمد النهائي';
COMMENT ON COLUMN "VRS"."VRS_REQUESTS"."DIR_EMPLOYEE_STATEMENT_CONFIRM" IS 'اعتماد مرئيات موظف الجهة';
COMMENT ON COLUMN "VRS"."VRS_REQUESTS"."TRANSACTION_NO" IS 'رقم المعاملة بالاتصالات الادارية';
COMMENT ON COLUMN "VRS"."VRS_REQUESTS"."REQUEST_PHASE_ID" IS 'مرحلة الطلب | حالة الطلب';
COMMENT ON COLUMN "VRS"."VRS_REQUESTS"."CREATED_USER" IS 'مقدم الطلب';

-----------------------------------------------------------------------------------------------------------------------

CREATE TABLE "VRS"."VRS_REQUEST_PHASES"
(	"REQUEST_PHASE_ID" NUMBER NOT NULL ENABLE,
     "REQUEST_ID" NUMBER NOT NULL ENABLE,
     "REQUEST_PHASE_SERIAL" NUMBER,
     "FROM_ROLE_ID" NUMBER,
     "TO_ROLE_ID" NUMBER,
     "FROM_PHASE_ID" NUMBER NOT NULL ENABLE,
     "TO_PHASE_ID" NUMBER NOT NULL ENABLE,
     "NOTES" VARCHAR2(4000 BYTE),
     "CREATED_DATE" DATE DEFAULT sysdate NOT NULL ENABLE,
     "CREATED_USER" VARCHAR2(15 BYTE) NOT NULL ENABLE,
     PRIMARY KEY ("REQUEST_PHASE_ID")
         USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
         STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
         PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
         BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
         TABLESPACE "USERS"  ENABLE
) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

COMMENT ON COLUMN "VRS"."VRS_REQUEST_PHASES"."REQUEST_PHASE_ID" IS 'مسلسل مرحلة الطلب';
COMMENT ON COLUMN "VRS"."VRS_REQUEST_PHASES"."REQUEST_ID" IS 'رقم الطلب';
COMMENT ON COLUMN "VRS"."VRS_REQUEST_PHASES"."REQUEST_PHASE_SERIAL" IS 'مسلسل مراحل الطلب';
COMMENT ON COLUMN "VRS"."VRS_REQUEST_PHASES"."FROM_ROLE_ID" IS 'الدور الوظيفي الحالي';
COMMENT ON COLUMN "VRS"."VRS_REQUEST_PHASES"."TO_ROLE_ID" IS 'الدور الوظيفي التالي';
COMMENT ON COLUMN "VRS"."VRS_REQUEST_PHASES"."FROM_PHASE_ID" IS 'مرحلة الطلب الحالية';
COMMENT ON COLUMN "VRS"."VRS_REQUEST_PHASES"."TO_PHASE_ID" IS 'مرحلة الطلب التالية';
COMMENT ON COLUMN "VRS"."VRS_REQUEST_PHASES"."NOTES" IS 'ملاحظات مرحلة الطلب';
COMMENT ON COLUMN "VRS"."VRS_REQUEST_PHASES"."CREATED_DATE" IS 'تاريخ ادخال مرحلة الطلب';
COMMENT ON COLUMN "VRS"."VRS_REQUEST_PHASES"."CREATED_USER" IS 'رقم المستخدم صاحب القرار';

-----------------------------------------------------------------------------------------------------------------------

CREATE TABLE "VRS"."VRS_REQUEST_WORK_TEAM"
(	"WORK_TEAM_ID" NUMBER NOT NULL ENABLE,
     "REQUEST_ID" NUMBER NOT NULL ENABLE,
     "EMPLOYEE_CODE" VARCHAR2(20 BYTE) NOT NULL ENABLE,
     "EMPLOYEE_NAME" VARCHAR2(200 BYTE),
     "JOB_TITLE" VARCHAR2(200 BYTE),
     "DIR_CODE" NUMBER,
     "EMPLOYEE_SERIAL" NUMBER NOT NULL ENABLE,
     CONSTRAINT "VRS_REQUEST_WORK_TEAM_PK" PRIMARY KEY ("WORK_TEAM_ID")
         USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
         STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
         PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
         BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
         TABLESPACE "USERS"  ENABLE
) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

COMMENT ON COLUMN "VRS"."VRS_REQUEST_WORK_TEAM"."WORK_TEAM_ID" IS 'مسلسل فريق العمل';
COMMENT ON COLUMN "VRS"."VRS_REQUEST_WORK_TEAM"."REQUEST_ID" IS 'رقم الطلب';
COMMENT ON COLUMN "VRS"."VRS_REQUEST_WORK_TEAM"."EMPLOYEE_CODE" IS 'كود الموظف';
COMMENT ON COLUMN "VRS"."VRS_REQUEST_WORK_TEAM"."EMPLOYEE_NAME" IS 'اسم الموظف';
COMMENT ON COLUMN "VRS"."VRS_REQUEST_WORK_TEAM"."JOB_TITLE" IS 'المسمي الوظيفي';
COMMENT ON COLUMN "VRS"."VRS_REQUEST_WORK_TEAM"."DIR_CODE" IS 'كود الادارة';
COMMENT ON COLUMN "VRS"."VRS_REQUEST_WORK_TEAM"."EMPLOYEE_SERIAL" IS 'تسلسل الموظفين لكل طلب';

-----------------------------------------------------------------------------------------------------------------------

