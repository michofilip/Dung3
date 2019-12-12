package repositories

import model.EntityName.TestDoor
import model.EntityType.Door
import model.{EntityName, EntityType}

class EntityTypeRepository {
    private val entityTypes: Map[EntityName, EntityType] = Map(TestDoor -> Door)
    
    def getEntityType(entityName: EntityName): Option[EntityType] = entityTypes.get(entityName)
}
