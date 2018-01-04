package com.example.bot.spring;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;


import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.beans.factory.annotation.Autowired;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.example.bot.spring.userinterface.UserInterface;
import com.example.bot.spring.userinterface.MenuInterface;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ChatBotController{
  @Autowired
	private ThreadPoolTaskScheduler threadPoolTaskScheduler;

  private HashMap<String, UserInterface> chatroom = new HashMap<String, UserInterface>();

  private UserInterface next = null;
  public void processInput(String replyToken, String id, TextMessageContent content){
    String text = content.getText();
    String userId = id;
    if(isNewChatRoom(userId)){
      setInterface(new MenuInterface(userId));
    }
    UserInterface ui = chatroom.get(userId);
    ui.process(text);
    // String result = ui.getMessage();

    // return result;
  }

  public void setInterface(UserInterface userInterface){
    String id = userInterface.getUserId();
    if(chatroom.containsKey(id)){
      chatroom.remove(id);
      log.info("hashmap remove:" + id);
    }

    userInterface.setController(this);
    chatroom.put(id, userInterface);
  }

  private boolean isNewChatRoom(String id){
    if(chatroom.containsKey(id))
      return false;
    return true;
  }

  public void schedulePushMsg(Runnable task, Date date){
    if(threadPoolTaskScheduler == null)
      log.info("threadPoolTaskScheduler is null");

    threadPoolTaskScheduler.schedule(task, date);
  }
}
