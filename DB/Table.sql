CREATE TABLE lang.work_rqst
(
  mgr_seq serial PRIMARY KEY,
  
  req_name character varying(20) NOT NULL,
  res_name character varying(20),
  
  req_lang text NOT NULL,
  res_lang text,
  
  req_time timestamp without time zone DEFAULT now(),
  res_time timestamp without time zone,
  
  etc_note text,
  cty_code smallint NOT NULL,
  prj_code character varying(5) NOT NULL
 
);

COMMENT ON TABLE lang.work_rqst IS '번역 요청 테이블';
COMMENT ON COLUMN lang.work_rqst.mgr_seq IS '관리_번호(주키)';

COMMENT ON COLUMN lang.work_rqst.req_name IS '번역요청_자';
COMMENT ON COLUMN lang.work_rqst.res_name IS '번역응답_자';

COMMENT ON COLUMN lang.work_rqst.req_lang IS '번역요청_글';
COMMENT ON COLUMN lang.work_rqst.res_lang IS '번역응답_글';

COMMENT ON COLUMN lang.work_rqst.req_time IS '번역요청_시간';
COMMENT ON COLUMN lang.work_rqst.res_time IS '번역응답_시간';

COMMENT ON COLUMN lang.work_rqst.etc_note IS '기타_비고';
COMMENT ON COLUMN lang.work_rqst.cty_code IS '구분_코드(1,2,3)';
COMMENT ON COLUMN lang.work_rqst.prj_code IS '프로젝트_코드(5자리 00001)';


CREATE OR REPLACE FUNCTION update_work_rqst_timestamp()
RETURNS TRIGGER AS $$
BEGIN
  NEW.res_time = NOW();
  RAISE NOTICE 'Setting update_time to: %', NEW.res_time;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trg_work_rqst_update
BEFORE UPDATE ON lang.work_rqst
FOR EACH ROW
EXECUTE PROCEDURE update_work_rqst_timestamp();
