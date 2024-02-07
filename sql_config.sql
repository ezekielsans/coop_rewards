---------------------------------------------------------------------------
CREATE OR REPLACE VIEW public.coop_rewards_view AS 
 SELECT DISTINCT cm.sc_acctno,
    concat(cm.last_name, ', ', cm.first_name, ', ', cm.middle_name) AS name
   FROM coop_member cm
UNION
 SELECT ca.sc_acctno,
    concat(ca.last_name, ', ', ca.first_name, ', ', ca.middle_name) AS name
   FROM coop_associate ca;


---------------------------------------------------------------------------


CREATE TABLE coop_rewards_member
(
member_id character varying,
amount numeric,
date date,
service_id integer,
FOREIGN KEY(service_id) REFERENCES coop_rewards_services (service_id)
)
----------------------------------------------------------------------------
CREATE TABLE coop_rewards_non_member
(
non_member_id character varying,
amount numeric,
date date,
service_id integer,
FOREIGN KEY(service_id) REFERENCES coop_rewards_services (service_id)
)


----------------------------------------------------------------------------
SELECT * FROM 
coop_rewards_services


      REFERENCES public.coop_sub_major_occu (sub_major_code) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,

---------------------------------------------------------------------------------------------
/*
FUNCTION FOR SUBMIT BUTTON
*/

  
CREATE OR REPLACE FUNCTION submit_coop_rewards(acctno CHARACTER VARYING,amount NUMERIC,service_id_ CHARACTER VARYING)
RETURNS CHARACTER VARYING AS
$BODY$

DECLARE
acctno character varying;

ins_acctno character varying;
ins_amount character varying;
ins_date character varying;
ins_service_id character varying;



BEGIN

IF acctno NOT IN (SELECT cm.mem_id_no
				  FROM coop_member  cm
				  JOIN coop_associate ca 
				  ON cm.mem_id_no = ca.reg_mem_no 
				  WHERE cm.mem_id_no := ins_acctno)
		  THEN 
		  
		  INSERT INTO coop_rewards_non_member (non_member_id,amount,date,service_id)
		  VALUES (ins_acctno,ins_amount,SELECT returncustomdate1(),ins_service_id);
		  
		  ELSE
		  
		  INSERT INTO coop_rewards_member (member_id,amount,date,service_id)
		  VALUES (ins_acctno,ins_amount,SELECT returncustomdate1(),ins_service_id);
		  
		  END IF;
		  
		  RETURN 'Account added'


END
$BODY$
 LANGUAGE plpgsql 
