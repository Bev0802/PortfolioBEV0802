from random import randint
from random import shuffle

from aiogram import Bot, Dispatcher, executor
from aiogram.dispatcher.filters import Command, Text
from aiogram.types import Message
from loguru import logger

from config import TOKEN

bot = Bot(TOKEN)
dp = Dispatcher(bot)

logger.add('log_info.log',
           format="{time} - {level} - {message}",
           level='DEBUG')

CANDIES = 121
CANDIES_LIMIT = 28
players = []
active_player = ""


@dp.message_handler(Command("start"))
async def choice_players(message: Message):
    await message.answer(text="Play with:\n"
                              "b - bot-human\n"
                              "h - person-human")


@dp.message_handler(Text(equals=["b", "h"]))
async def lets_play(message: Message):
    global players, active_player
    n = 1 if message.text == "b" else 0
    players = ["human", 'bot' if n else 'person']
    shuffle(players)
    active_player = players[0]
    await message.answer(f'1 player - {players[0]}, 2 player - {players[1]}')
    await message.answer("Let's play.\n"
                         "y - yes\n"
                         "n - no\n")


@dp.message_handler(Text(equals=["n"]))
async def stop_game(message: Message):
    await message.answer(f"Good bye!")
    dp.stop_polling()
    await dp.wait_closed()
    await bot.close()


def bot_run(candies: int) -> int:
    result = candies % 29
    if not result:
        result = randint(1, 28)
    return result


def moves(pl_act):
    first, second = players
    return second if pl_act == first else first


def game_step(sweets):
    global CANDIES
    CANDIES -= sweets


@dp.message_handler(Text(equals=[*range(1, 29), "y"]))
async def game(message: Message):
    global active_player, CANDIES

    if message.text.isdigit():
        get_candies = int(message.text)
        game_step(get_candies)
    if CANDIES > 0:
        await message.answer(f'\nThere are {CANDIES} sweets on the table, you can take [1 .. {CANDIES_LIMIT}]')
        if active_player == "bot":
            await message.answer(f"Player {active_player}'s move")
            get_candies = bot_run(CANDIES)
            CANDIES -= get_candies
            await message.answer(f'The bot took {get_candies} candies. There are {CANDIES} sweets on the table')
            if CANDIES > 0:
                active_player = moves(active_player)
                await message.answer(f"Player {active_player}'s move")
                await message.answer(f'How many candies do you want {active_player}: ')
                active_player = moves(active_player)
            else:
                await message.answer(f'The player {active_player} won!')
                CANDIES = 121
        else:
            await message.answer(f"Player {active_player}'s move")
            await message.answer(f'How many candies do you want {active_player}: ')

            active_player = moves(active_player)
    else:
        active_player = moves(active_player)
        await message.answer(f'The player {active_player} won!')
        CANDIES = 121


@dp.message_handler()
async def echo(message: Message):
    logger.debug('Не верный ввод пользователем')
    await message.answer(f'{message.from_user.first_name},'
                         f' please, enter the correct message')


executor.start_polling(dp)
