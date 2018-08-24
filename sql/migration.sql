select count(*) as count, DATE(a.date_created) as date, 
	m1.value as author, m2.value as artist, m3.value as book_publisher, m4.value as publisher, 
	m5.value as aggregator, m6.value as album, s.title 
	from store_activity a, content c, content_meta_data m1, content_meta_data m2, content_meta_data m3, 
		content_meta_data m4, content_meta_data m5, content_meta_data m6, service_definition s 
	where c.id = m1.content_id and c.id = m2.content_id and c.id = m3.content_id 
		and c.id = m4.content_id and c.id = m5.content_id and s.id = c.service_id 
		and m1.key_ = 'id3_Author' and m2.key_ = 'id3_Artist' and m3.key_ = 'Book_Publisher' 
		and m4.key_ = 'id3_Publisher' and m5.key_ = 'Aggregator' and m6.key_ = 'id3_Album' 
		and a.content_id = c.id group by DATE(a.date_created), m6.value;