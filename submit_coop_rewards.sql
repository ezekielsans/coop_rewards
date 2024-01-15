
CREATE OR REPLACE FUNCTION public.submit_coop_rewards(
    acctno character varying,
    amount numeric,
    service_id_ integer)
  RETURNS character varying AS
$BODY$

DECLARE
customdate date;
BEGIN

customdate := (SELECT returncustomdate1());

IF acctno NOT IN (SELECT sc_acctno 
			    FROM coop_member 
		            UNION SELECT sc_acctno 
			    FROM coop_associate)

		  THEN 
		  
		     INSERT INTO coop_rewards_non_member (non_member_id,amount,date,service_id)
		     VALUES (acctno,amount,customdate,service_id_);
		 
		  ELSE
		 
		  INSERT INTO coop_rewards_member (acctno,amount,date,service_id)
		  VALUES (acctno,amount,customdate,service_id_);
		  
		  END IF;
		  
		  RETURN 'Account added';


END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
