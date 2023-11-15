package dnd.monster_service.rabbitMq.transport_entity.Monster;

import org.springframework.stereotype.Component;

@Component
public class MessageMqFactory {

    public MonsterMq monsterCreatedMessage(long Id, String name, float cr, boolean homebrew){
        return new MonsterMq(Id, name, cr, "monsterCreated", homebrew);
    }

    public MonsterMq monsterUpdatedMessage(long Id, String name, float cr, boolean homebrew){
        return new MonsterMq(Id, name, cr, "monsterUpdated", homebrew);
    }

    public IdMessageMq monsterDeletedMessage(long Id){
        return new IdMessageMq( "monsterDeleted", Id);
    }

    public MonsterGroupMq monsterRemovedFromGroup(long Id, long groupId){
        return new MonsterGroupMq( "monsterDeleted", Id, groupId);
    }

    public MonsterGroupMq monsterAddedToGroup(long Id, long groupId){
        return new MonsterGroupMq( "monsterAddedToGroup", Id, groupId);
    }

    public GroupMq monsterGroupCreated(long Id, String name){
        return new GroupMq( "monsterGroupCreated", Id, name);
    }

    public GroupMq monsterGroupUpdated(long Id, String name){
        return new GroupMq( "monsterGroupUpdated", Id, name);
    }

    public IdMessageMq monsterGroupDeleted(long Id){
        return new IdMessageMq( "monsterGroupDeleted", Id);
    }

}
