REM INSERTING into VRS_ROLES
SET DEFINE OFF;
Insert into VRS_ROLES (ROLE_ID,ROLE_DESC,IS_ACTIVE) values (1,'مدير الادارة',1);
Insert into VRS_ROLES (ROLE_ID,ROLE_DESC,IS_ACTIVE) values (2,'أعضاء لجنة المراجعة الداخلية',1);
Insert into VRS_ROLES (ROLE_ID,ROLE_DESC,IS_ACTIVE) values (3,'الموظف المعني بالجهة',1);
Insert into VRS_ROLES (ROLE_ID,ROLE_DESC,IS_ACTIVE) values (4,'المدير المباشر للموظف',1);
Insert into VRS_ROLES (ROLE_ID,ROLE_DESC,IS_ACTIVE) values (5,'المعتمد النهائي',1);
Insert into VRS_ROLES (ROLE_ID,ROLE_DESC,IS_ACTIVE) values (6,'مرئيات أعضاء لجنة المراجعة الداخلية',1);

-----------------------------------------------------------------------------------------------------------------------

REM INSERTING into VRS_PHASES
SET DEFINE OFF;
Insert into VRS_PHASES (PHASE_ID,PHASE_NAME,PHASE_DESC,FROM_ROLE_NO,TO_ROLE_NO,BENEFICIARY_MESSAGE,IS_ACTIVE,NOTES) values (1,'تقديم الطلب','طلب جديد',1,2,'تم تسجيل طلب محضر التحقق من قبل مدير الادارة',1,null);
Insert into VRS_PHASES (PHASE_ID,PHASE_NAME,PHASE_DESC,FROM_ROLE_NO,TO_ROLE_NO,BENEFICIARY_MESSAGE,IS_ACTIVE,NOTES) values (2,'إلغاء الطلب','طلب ملغي',1,1,'تم إلغاء الطلب من قبل مدير الادارة',1,null);
Insert into VRS_PHASES (PHASE_ID,PHASE_NAME,PHASE_DESC,FROM_ROLE_NO,TO_ROLE_NO,BENEFICIARY_MESSAGE,IS_ACTIVE,NOTES) values (3,'إعتماد الطلب','طلب معتمد من لجنة المراجعة الداخلية | اختيار موظف الجهة',2,3,'تم اختيار موظف الجهة من قبل أعضاء لجنة المراجعة الداخلية',1,null);
Insert into VRS_PHASES (PHASE_ID,PHASE_NAME,PHASE_DESC,FROM_ROLE_NO,TO_ROLE_NO,BENEFICIARY_MESSAGE,IS_ACTIVE,NOTES) values (4,'إعتماد الطلب','طلب معتمد من موظف الجهة',3,4,'تم تسجيل افادة من قبل موظف الجهة',1,null);
Insert into VRS_PHASES (PHASE_ID,PHASE_NAME,PHASE_DESC,FROM_ROLE_NO,TO_ROLE_NO,BENEFICIARY_MESSAGE,IS_ACTIVE,NOTES) values (5,'إعتماد الطلب','طلب معتمد من المدير المباشر',4,5,'تم تسجيل مرئيات المدير المباشر علي افادة الموظف',1,null);
Insert into VRS_PHASES (PHASE_ID,PHASE_NAME,PHASE_DESC,FROM_ROLE_NO,TO_ROLE_NO,BENEFICIARY_MESSAGE,IS_ACTIVE,NOTES) values (6,'رفض الطلب','طلب مرفوض المدير المباشر | اضافة مرحلة اعتماد ثانية',4,6,'تم اضافة مرحلة اعتماد ثانية من قبل المدير المباشر علي افادة الموظف',1,null);
Insert into VRS_PHASES (PHASE_ID,PHASE_NAME,PHASE_DESC,FROM_ROLE_NO,TO_ROLE_NO,BENEFICIARY_MESSAGE,IS_ACTIVE,NOTES) values (7,'إعتماد الطلب','طلب معتمد من المعتمد النهائى',5,6,'تم تسجيل مرئيات المعتمد النهائي علي افادة الموظف',1,null);
Insert into VRS_PHASES (PHASE_ID,PHASE_NAME,PHASE_DESC,FROM_ROLE_NO,TO_ROLE_NO,BENEFICIARY_MESSAGE,IS_ACTIVE,NOTES) values (8,'إعتماد الطلب','طلب معتمد من لجنة المراجعة الداخلية | اغلاق الطلب',6,6,'تم تأكيد افادة الموظف الجهة من قبل أعضاء لجنة المراجعة الداخلية | اغلاق الطلب',1,null);

-----------------------------------------------------------------------------------------------------------------------

REM INSERTING into VRS_PHASE_ACTIONS
SET DEFINE OFF;
Insert into VRS_PHASE_ACTIONS (PHASE_ACTION_ID,PARENT_PHASE_ID,CHILD_PHASE_ID,IS_ACTIVE,ACTION_ORDER,NOTES) values (1,1,1,1,1,null);
Insert into VRS_PHASE_ACTIONS (PHASE_ACTION_ID,PARENT_PHASE_ID,CHILD_PHASE_ID,IS_ACTIVE,ACTION_ORDER,NOTES) values (2,1,2,1,3,null);
Insert into VRS_PHASE_ACTIONS (PHASE_ACTION_ID,PARENT_PHASE_ID,CHILD_PHASE_ID,IS_ACTIVE,ACTION_ORDER,NOTES) values (3,1,3,1,2,null);
Insert into VRS_PHASE_ACTIONS (PHASE_ACTION_ID,PARENT_PHASE_ID,CHILD_PHASE_ID,IS_ACTIVE,ACTION_ORDER,NOTES) values (4,3,4,1,1,null);
Insert into VRS_PHASE_ACTIONS (PHASE_ACTION_ID,PARENT_PHASE_ID,CHILD_PHASE_ID,IS_ACTIVE,ACTION_ORDER,NOTES) values (5,4,5,1,1,null);
Insert into VRS_PHASE_ACTIONS (PHASE_ACTION_ID,PARENT_PHASE_ID,CHILD_PHASE_ID,IS_ACTIVE,ACTION_ORDER,NOTES) values (6,4,6,1,2,null);
Insert into VRS_PHASE_ACTIONS (PHASE_ACTION_ID,PARENT_PHASE_ID,CHILD_PHASE_ID,IS_ACTIVE,ACTION_ORDER,NOTES) values (7,5,7,1,1,null);
Insert into VRS_PHASE_ACTIONS (PHASE_ACTION_ID,PARENT_PHASE_ID,CHILD_PHASE_ID,IS_ACTIVE,ACTION_ORDER,NOTES) values (8,6,8,1,1,null);
Insert into VRS_PHASE_ACTIONS (PHASE_ACTION_ID,PARENT_PHASE_ID,CHILD_PHASE_ID,IS_ACTIVE,ACTION_ORDER,NOTES) values (9,7,8,1,1,null);
