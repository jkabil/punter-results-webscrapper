# punter-results-webscrapper
jsoup webscrapper for scrapping results from https://www.punters.com.au/racing-results/.
Input: file path and date
Output: results for input date scrapped into csv file
#How to Run
java -jar ScrapePunterResults.jar <filepath> [<date in YYYY-MM-DD format>]
where
  filepath - location where the result (in csv format) to be stored
  date - optional date to indicate which date result should be scrapped; if not given, default date will be current date.

