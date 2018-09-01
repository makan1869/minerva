today=`date  +"%Y-%m-%d"`;
#yesterday=`date  +"%Y-%m-%d" --date="yesterday"`;
yesterday=`date -v -1d +"%Y-%m-%d"`;
echo "insert into nightly_statistics (count, date, aggregator, album, artist, author, book_publisher, publisher, service) select count(*) as count, date, aggregator, album, artist, author, book_publisher, publisher, service from activity where date_created > '$yesterday' and date_created < '$today'  group by date, aggregator, album, artist, author, book_publisher, publisher, service;" > backup.sql
echo "delete from daily_activity where date_created < '$today';" >> backup.sql
mysql -u root publishers < backup.sql