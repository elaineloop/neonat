package info.shuige.neonat.ui.controler;


import java.text.MessageFormat;
import java.util.List;
import java.util.StringTokenizer;

import info.shuige.neonat.entity.Board;
import info.shuige.neonat.entity.Topic;
import info.shuige.neonat.service.NeoatModelService;
import info.shuige.neonat.ui.console.Activator;

import org.osgi.framework.BundleContext;

public class ConsoleCommandor {

	
	private ConsoleSession session;
	private Board board;
	
	public ConsoleCommandor(ConsoleSession consoleSession) {
		this.session = consoleSession;
		BundleContext context = Activator.getContext();
		NeoatModelService ms = context.getService(context.getServiceReference(NeoatModelService.class));
		this.board = ms.getBoard();
	}

	public void doCommand(String cmd) {
		cmd = cmd.charAt(0) == 65533 ? cmd.substring(14) : cmd;
		StringTokenizer st = new StringTokenizer(cmd);
		try{
			ConsoleCommandor.class.getDeclaredMethod(st.nextToken().toLowerCase()+"Action", StringTokenizer.class).invoke(this, st);
			
		}catch(Exception e){
			if( e instanceof NoSuchMethodException){
				session.getWriter().print("非法命令:"+cmd);
			}else{
				e.printStackTrace(session.getWriter());
			}
			
			prompt();
		}
	}

	private void prompt() {
		session.getWriter().print(TempletRepository.PROMPT);
		session.getWriter().flush();
	}
	void helpAction(StringTokenizer st){
		session.getWriter().print(TempletRepository.HELP_PAGE);
		
	}
	void welcomeAction(StringTokenizer st){
		session.getWriter().print(TempletRepository.WELCOME_PAGE);
	}
	void topicksAction(StringTokenizer st){
		session.getWriter().print(MessageFormat.format(TempletRepository.BOARD_PAGE,board.getName(),board.getTopicCount() ));
		printTopic(board.getTopics(),1);
		
	}
	private void printTopic(List<Topic> topics, int level) {
		for(Topic topic : topics){
			for(int i=0;i<level -1; i++){
				session.getWriter().print("        ");
			}
			session.getWriter().print("  |--");
			session.getWriter().println("["+topic.getId()+"]"+topic.getTitle());
			printTopic(topic.getReplyTopics(), level+1);
		}
	}

	void viewAction(StringTokenizer st){
		int id  = Integer.parseInt(st.nextToken());
		Topic topic = board.getTopicMap().get(id);
		if(null == topic){
			throw new IllegalArgumentException("无效帖子ID");
		}
		session.getWriter().println(MessageFormat.format(TempletRepository.TOPIC_PAGE, topic.getTitle(),topic.getDate(),topic.getContent()));
	}
	void replyAction(StringTokenizer st){
		int id = Integer.parseInt(st.nextToken());
		String title = st.nextToken();
		String content = st.nextToken();
		board.addTopic(id, title, content);
	}
	void newAction(StringTokenizer st){
		String title = st.nextToken();
		String content = st.nextToken();
		board.addTopic(0, title, content);
	}

	
}
