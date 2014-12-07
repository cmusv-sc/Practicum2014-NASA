select 
rel.publicationId,
GROUP_CONCAT(rel.authorId)
from dblp.AuthorPublicationMap as rel 
where rel.publicationId in (
select pub.publicationId from dblp.Publication as pub where pub.publicationTitle like '%Cloud Computing%'
)
group by rel.publicationId