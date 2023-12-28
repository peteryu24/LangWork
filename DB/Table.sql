CREATE TABLE lang.work_rqst
(
  content_id serial PRIMARY KEY,
  writer_id character varying(20) NOT NULL,
  editor_id character varying(20),
  category smallint NOT NULL,
  kor_lang text NOT NULL,
  en_lang text,
  note text,
  project_code character varying(5) NOT NULL,
  create_time timestamp without time zone DEFAULT now(),
  update_time timestamp without time zone
);

COMMENT ON TABLE lang.work_rqst IS '번역 요청 테이블';
COMMENT ON COLUMN lang.work_rqst.content_id IS '글 번호(주키)';
COMMENT ON COLUMN lang.work_rqst.writer_id IS '국문 작성자 ID';
COMMENT ON COLUMN lang.work_rqst.editor_id IS '번역자 ID';
COMMENT ON COLUMN lang.work_rqst.category IS '구분(1,2,3)';
COMMENT ON COLUMN lang.work_rqst.kor_lang IS '국문';
COMMENT ON COLUMN lang.work_rqst.en_lang IS '영문';
COMMENT ON COLUMN lang.work_rqst.note IS '비고';
COMMENT ON COLUMN lang.work_rqst.project_code IS '프로젝트 코드(5자리 00001)';
COMMENT ON COLUMN lang.work_rqst.create_time IS '게시글 작성 날짜시간';
COMMENT ON COLUMN lang.work_rqst.update_time IS '게시글 번역 날짜시간';

CREATE OR REPLACE FUNCTION update_work_rqst_timestamp()
RETURNS TRIGGER AS $$
BEGIN
  NEW.update_time = NOW();
  RAISE NOTICE 'Setting update_time to: %', NEW.update_time;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trg_work_rqst_update
BEFORE UPDATE ON lang.work_rqst
FOR EACH ROW
EXECUTE PROCEDURE update_work_rqst_timestamp();
