package repository

import entity.Entity
import model.position.Coordinates

class EntityRepository private(private val entitiesById: Map[Long, Entity],
                               private val entitiesByCoordinates: Map[Coordinates, Map[Long, Entity]]) {
    
    def +(entity: Entity): EntityRepository = {
        val newEntitiesById = entitiesById + (entity.id -> entity)
        
        val newEntitiesByCoordinates = entity.positionOpt.map(_.coordinates).map(coordinates => {
            val newEntitiesAtCoordinates = entitiesByCoordinates.getOrElse(coordinates, Map.empty) + (entity.id -> entity)
            entitiesByCoordinates + (coordinates -> newEntitiesAtCoordinates)
        }).getOrElse(entitiesByCoordinates)
        
        new EntityRepository(newEntitiesById, newEntitiesByCoordinates)
    }
    
    def ++(entities: Seq[Entity]): EntityRepository =
        entities.foldLeft(this)((entityRepository, entity) => entityRepository + entity)
    
    def -(entity: Entity): EntityRepository = {
        val newEntitiesById = entitiesById - entity.id
        
        val newEntitiesByCoordinates = entity.positionOpt.map(_.coordinates).map(coordinates => {
            val newEntitiesAtCoordinates = entitiesByCoordinates.getOrElse(coordinates, Map.empty) - entity.id
            if (newEntitiesAtCoordinates.isEmpty) entitiesByCoordinates - coordinates
            else entitiesByCoordinates + (coordinates -> newEntitiesAtCoordinates)
        }).getOrElse(entitiesByCoordinates)
        
        new EntityRepository(newEntitiesById, newEntitiesByCoordinates)
    }
    
    def --(entities: Seq[Entity]): EntityRepository =
        entities.foldLeft(this)((entityRepository, entity) => entityRepository - entity)
    
    def contains(id: Long): Boolean = entitiesById.contains(id)
    
    def contains(coordinates: Coordinates): Boolean = entitiesByCoordinates.contains(coordinates)
    
    def getAll: Vector[Entity] = entitiesById.values.toVector
    
    def getById(id: Long): Option[Entity] = entitiesById.get(id)
    
    def getByCoordinates(coordinates: Coordinates): Vector[Entity] =
        entitiesByCoordinates.getOrElse(coordinates, Map.empty).values.toVector
    
    def existsAtCoordinates(coordinates: Coordinates, condition: Entity => Boolean): Boolean =
        getByCoordinates(coordinates).exists(condition)
    
    def forallAtCoordinates(coordinates: Coordinates, condition: Entity => Boolean): Boolean =
        getByCoordinates(coordinates).forall(condition)
    
}

object EntityRepository {
    def apply(): EntityRepository = new EntityRepository(Map.empty, Map.empty)
    
    def apply(entities: Seq[Entity]): EntityRepository = EntityRepository() ++ entities
}


