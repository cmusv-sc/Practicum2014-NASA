select 
rel.authorId,
(select au.authorName from dblp.Author as au where au.authorId = rel.authorId) as AuthorName,
GROUP_CONCAT((select CONCAT(p.publicationTitle, ' (', p.Year, ')') from dblp.Publication as p where p.publicationId = rel.publicationId) SEPARATOR '~') as PublicationList,
COUNT(rel.publicationId) as PublicationCount
from dblp.AuthorPublicationMap as rel 
where rel.publicationId in (
select pub.publicationId from dblp.Publication as pub where pub.publicationTitle like '%Cloud Computing%'
)
group by rel.authorId order by rel.authorId;