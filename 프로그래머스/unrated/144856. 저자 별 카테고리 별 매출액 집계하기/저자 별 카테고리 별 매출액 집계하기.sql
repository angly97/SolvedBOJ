-- 코드를 입력하세요
SELECT A.AUTHOR_ID, AUTHOR_NAME, CATEGORY, (SUM(SALES * PRICE)) SALES
FROM BOOK_SALES BS
JOIN BOOK B ON B.BOOK_ID = BS.BOOK_ID
JOIN AUTHOR A ON A.AUTHOR_ID = B.AUTHOR_ID
WHERE SALES_DATE LIKE '2022-01%'
GROUP BY A.AUTHOR_ID, B.CATEGORY
ORDER BY A.AUTHOR_ID, CATEGORY DESC;