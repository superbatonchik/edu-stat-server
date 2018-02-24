alter table activity_type rename column activity_type_id to id;
alter table credentials rename column credentials_id to id;
alter table document_format rename column document_format_id to id;
alter table edu rename column edu_id to id;
alter table edu_kind rename column edu_kind_id to id;
alter table edu_status rename column edu_status_id to id;
alter table edu_type rename column edu_type_id to id;
alter table file rename column file_id to id;
alter table form rename column form_id to id;
alter table form_type rename column form_type_id to id;
alter table management_agency rename column management_agency_id to id;
alter table management_agency_activity rename column management_agency_activity_id to id;
alter table message rename column message_id to id;
alter table municipality rename column municipality_id to id;
alter table ownership_type rename column ownership_type_id to id;
alter table region rename column region_id to id;
alter table settlement_type rename column settlement_type_id to id;

ALTER TABLE public.file ADD COLUMN file_path VARCHAR(255) DEFAULT 'empty' NOT NULL,
  ADD COLUMN file_name VARCHAR(255) DEFAULT 'empty' NOT NULL;

ALTER table mm_regular__summary_form ADD COLUMN file_id INT REFERENCES file(id);
